package com.github.dmytr0.vacation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVacationsStats {

    private VacationUsageDto vacationUsage;
    private int availableDate;
    private LocalDate onDate;
}
