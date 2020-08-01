package com.github.dmytr0.vacation.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDate;

@Data
@Entity(name = "vacationRecord")
@EqualsAndHashCode(exclude = "_id")
@NoArgsConstructor
public class VacationRecord {

    private String _id;
    private String userName;
    private LocalDate recordDate;

    public VacationRecord(String userName, LocalDate recordDate) {
        this.userName = userName;
        this.recordDate = recordDate;
    }
}
