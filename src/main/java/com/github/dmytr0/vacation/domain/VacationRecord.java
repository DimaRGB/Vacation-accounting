package com.github.dmytr0.vacation.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDate;

@Data
@Entity(name = "vacationRecord")
@NoArgsConstructor
public class VacationRecord {

    private String _id;
    private String userName;
    private LocalDate recordDate;
}
