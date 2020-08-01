package com.github.dmytr0.vacation.service;


import com.github.dmytr0.vacation.domain.EmploymentInfo;
import com.github.dmytr0.vacation.domain.VacationRecord;
import com.github.dmytr0.vacation.dto.SpentVacationDetails;
import com.github.dmytr0.vacation.dto.UserVacationsStats;
import com.github.dmytr0.vacation.dto.VacationUsageDto;
import com.github.dmytr0.vacation.exception.UserException;
import com.github.dmytr0.vacation.repository.EmploymentInfoRepository;
import com.github.dmytr0.vacation.repository.VacationRecordRepository;
import com.github.dmytr0.vacation.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@Log4j2
@Service
@RequiredArgsConstructor
public class VacationCalculationService {

    private final EmploymentInfoRepository employmentInfoRepository;
    private final VacationRecordRepository vacationRecordRepository;
    private final CommonUtils commonUtils;

    public UserVacationsStats calc(String userName, LocalDate calcDate) {
        EmploymentInfo employmentInfo = employmentInfoRepository.findByUserName(userName)
                .orElseThrow(() -> new UserException("Employment info not found for user {0}", userName));

        VacationUsageDto vacationUsage = getVacationUsage(userName, calcDate, employmentInfo.getEmploymentDate());
        Map<Integer, Integer> notReservedDaysPerYear = vacationUsage.getAllVacationDays();

        int remainingVacationDays = notReservedDaysPerYear.get(calcDate.getYear());
        if (commonUtils.isBeforeReset(calcDate)) {
            remainingVacationDays += notReservedDaysPerYear.get(calcDate.getYear() - 1);
        }

        return new UserVacationsStats(vacationUsage, remainingVacationDays, calcDate);
    }


    private VacationUsageDto getVacationUsage(String name, LocalDate onDate, LocalDate employmentDay) {
        Map<LocalDate, SpentVacationDetails> spentDayAtTheExpenseOfTheYear = new HashMap<>();

        Map<Integer, Integer> allVacationDaysPerYear = getAllVacationDaysPerYear(onDate, employmentDay);

        List<LocalDate> spentVacationDates = new ArrayList<>(getSpentVacations(name)).stream()
                .filter(ld -> ld.isBefore(onDate) || ld.equals(onDate))
                .collect(toList());

        for (LocalDate vacationDate : spentVacationDates) {
            int vacationYear = vacationDate.getYear();
            int prevVacationYear = vacationYear - 1;

            Integer availableDays = allVacationDaysPerYear.get(vacationYear);
            Integer prevAvailableDays = allVacationDaysPerYear.get(prevVacationYear);
            boolean prevPeriodExist = allVacationDaysPerYear.containsKey(prevVacationYear);

            SpentVacationDetails spentVacationDetails = new SpentVacationDetails();
            if (commonUtils.isAfterReset(vacationDate)) {
                if (availableDays <= 0) {
                    System.err.println("Detected usage of vacation At The Expense of future vacations");
                    spentVacationDetails.setDebtWriteOff(true);
                }
                spentVacationDetails.setWrittenOfYear(vacationYear);
                spentDayAtTheExpenseOfTheYear.put(vacationDate, spentVacationDetails);
                allVacationDaysPerYear.replace(vacationYear, availableDays, --availableDays);

            } else {
                if (prevPeriodExist && prevAvailableDays > 0) {
                    System.out.println("Usage of vacation At The Expense of previous period");
                    spentVacationDetails.setWrittenOfYear(prevVacationYear);
                    allVacationDaysPerYear.replace(prevVacationYear, prevAvailableDays, --prevAvailableDays);
                } else {
                    spentVacationDetails.setWrittenOfYear(vacationYear);
                    allVacationDaysPerYear.replace(vacationYear, availableDays, --availableDays);
                }
            }

            spentDayAtTheExpenseOfTheYear.put(vacationDate, spentVacationDetails);
        }
        calculationPostProcessing(onDate, employmentDay, allVacationDaysPerYear);

        commonUtils.printMap("allVacationDaysPerYear ", allVacationDaysPerYear, Integer::compareTo);
        commonUtils.printMap("spentDayAtTheExpenseOfTheYear ", spentDayAtTheExpenseOfTheYear, LocalDate::compareTo);

        return new VacationUsageDto(spentDayAtTheExpenseOfTheYear, allVacationDaysPerYear);
    }

    private void calculationPostProcessing(LocalDate onDate, LocalDate employmentDay, Map<Integer, Integer> allVacationDays) {
        // Keep negative balance
        int endYear = onDate.getYear();
        for (int i = employmentDay.getYear() + 1; i <= endYear; i++) {
            Integer currentDays = allVacationDays.get(i);
            Integer prevDays = allVacationDays.get(i - 1);
            if (prevDays != null && prevDays < 0) {
                currentDays += prevDays;
                allVacationDays.replace(i, currentDays);
                allVacationDays.replace(i - 1, 0);
            }

            // reset prev balance after reset date
            if (commonUtils.isAfterReset(commonUtils.min(onDate, commonUtils.endOfYear(i)))) {
                allVacationDays.replace(i - 1, 0);
            }
        }
    }


    private Map<Integer, Integer> getAllVacationDaysPerYear(LocalDate onDate, LocalDate employmentDay) {
        Map<Integer, Integer> allVacationDaysPerYear = new HashMap<>();

        for (int currentYear = employmentDay.getYear(); currentYear <= onDate.getYear(); currentYear++) {

            LocalDate startCalcDate = commonUtils.max(commonUtils.startOfYear(currentYear), employmentDay);
            LocalDate endCalcDate = commonUtils.min(commonUtils.endOfYear(currentYear), onDate);
            int datesPeriod = commonUtils.daysDiff(startCalcDate, endCalcDate);
            float vacationDays = commonUtils.calculateVacationDays(datesPeriod);
            allVacationDaysPerYear.put(currentYear, (int) vacationDays);
        }
        return allVacationDaysPerYear;
    }

    public List<LocalDate> getSpentVacations(String username) {
        return vacationRecordRepository.findByUserName(username).stream()
                .map(VacationRecord::getRecordDate)
                .sorted(LocalDate::compareTo)
                .collect(toList());
    }
}
