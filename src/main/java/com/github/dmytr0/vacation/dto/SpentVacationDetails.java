package com.github.dmytr0.vacation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SpentVacationDetails {

    private int writtenOfYear;
    private boolean debtWriteOff;

    public SpentVacationDetails(int writtenOfYear) {
        this.writtenOfYear = writtenOfYear;
    }
}
