package com.github.dmytr0.vacation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Map;

@AllArgsConstructor
@Data
public class VacationUsageDto {
    private Map<LocalDate, SpentVacationDetails> spentDayAtTheExpenseOfTheYear;
    private Map<Integer, Integer> allVacationDays;
}
