package com.github.dmytr0.vacation.dto;

import com.github.dmytr0.vacation.domain.VacationRecord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVacationsDto {

    private UserDto user;
    private List<VacationRecord> vacations = new ArrayList<>();
}
