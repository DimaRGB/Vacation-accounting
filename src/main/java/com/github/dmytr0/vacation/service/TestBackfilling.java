package com.github.dmytr0.vacation.service;

import com.github.dmytr0.vacation.domain.EmploymentInfo;
import com.github.dmytr0.vacation.domain.User;
import com.github.dmytr0.vacation.domain.VacationRecord;
import com.github.dmytr0.vacation.repository.EmploymentInfoRepository;
import com.github.dmytr0.vacation.repository.UsersRepository;
import com.github.dmytr0.vacation.repository.VacationRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TestBackfilling {

    public static final String USER = "Taras.Karas";
    public static final String USER_EMAIL = USER + "@auto1.com";
    private final EmploymentInfoRepository employmentInfoRepository;
    private final UsersRepository usersRepository;
    private final VacationRecordRepository vacationRecordRepository;

//    @PostConstruct
    public void back() {
//        fillEmploymentInfo();
//        fillUser();
//        filVacations();
    }

    private void filVacations() {
        List<VacationRecord> list = List.of(
                new VacationRecord(USER_EMAIL, LocalDate.of(2019, 5, 2)),
                new VacationRecord(USER_EMAIL, LocalDate.of(2019, 5, 3)),
                new VacationRecord(USER_EMAIL, LocalDate.of(2019, 8, 5)),
                new VacationRecord(USER_EMAIL, LocalDate.of(2019, 9, 6)),
                new VacationRecord(USER_EMAIL, LocalDate.of(2019, 9, 12)),
                new VacationRecord(USER_EMAIL, LocalDate.of(2019, 11, 9)),
                new VacationRecord(USER_EMAIL, LocalDate.of(2019, 12, 10)),
                new VacationRecord(USER_EMAIL, LocalDate.of(2019, 12, 11)),
                new VacationRecord(USER_EMAIL, LocalDate.of(2019, 12, 12)),
                new VacationRecord(USER_EMAIL, LocalDate.of(2019, 12, 14)),
                new VacationRecord(USER_EMAIL, LocalDate.of(2019, 12, 16)),
                new VacationRecord(USER_EMAIL, LocalDate.of(2019, 12, 17)),
                new VacationRecord(USER_EMAIL, LocalDate.of(2019, 12, 18)),
                new VacationRecord(USER_EMAIL, LocalDate.of(2020, 2, 17)),
                new VacationRecord(USER_EMAIL, LocalDate.of(2020, 2, 18)),
                new VacationRecord(USER_EMAIL, LocalDate.of(2020, 8, 17)),
                new VacationRecord(USER_EMAIL, LocalDate.of(2020, 8, 18)),
                new VacationRecord(USER_EMAIL, LocalDate.of(2020, 8, 19)),
                new VacationRecord(USER_EMAIL, LocalDate.of(2020, 8, 20)),
                new VacationRecord(USER_EMAIL, LocalDate.of(2020, 8, 21)),
                new VacationRecord(USER_EMAIL, LocalDate.of(2020, 12, 24)),
                new VacationRecord(USER_EMAIL, LocalDate.of(2020, 12, 28)),
                new VacationRecord(USER_EMAIL, LocalDate.of(2020, 12, 29)),
                new VacationRecord(USER_EMAIL, LocalDate.of(2020, 12, 30)),
                new VacationRecord(USER_EMAIL, LocalDate.of(2020, 12, 31)),
                new VacationRecord(USER_EMAIL, LocalDate.of(2021, 1, 4)),
                new VacationRecord(USER_EMAIL, LocalDate.of(2021, 1, 5)),
                new VacationRecord(USER_EMAIL, LocalDate.of(2021, 1, 6)),
                new VacationRecord(USER_EMAIL, LocalDate.of(2021, 1, 8)),
                new VacationRecord(USER_EMAIL, LocalDate.of(2021, 2, 9)),
                new VacationRecord(USER_EMAIL, LocalDate.of(2021, 2, 10)),
                new VacationRecord(USER_EMAIL, LocalDate.of(2021, 2, 11)),
                new VacationRecord(USER_EMAIL, LocalDate.of(2021, 2, 12))
        );
        vacationRecordRepository.saveAll(list);
    }

    private void fillUser() {
        User user = usersRepository.findByEmail(USER_EMAIL)
                .orElseGet(() -> new User().setEmail(USER_EMAIL).setName(USER));
        user.setTeam("Jedi");
        usersRepository.save(user);
    }

    private void fillEmploymentInfo() {
        EmploymentInfo user = new EmploymentInfo(USER_EMAIL, LocalDate.of(2018, 12, 1));
        employmentInfoRepository.save(user);
    }

}
