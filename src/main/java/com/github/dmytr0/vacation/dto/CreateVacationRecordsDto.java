package com.github.dmytr0.vacation.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CreateVacationRecordsDto {

    private String userName;
    private List<LocalDate> dates;

}
