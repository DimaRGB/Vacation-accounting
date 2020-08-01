package com.github.dmytr0.vacation.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

@Data
public class CreateVacationRecordsDto {

    @NotBlank
    private String userName;

    @NotEmpty
    private List<LocalDate> dates;

}
