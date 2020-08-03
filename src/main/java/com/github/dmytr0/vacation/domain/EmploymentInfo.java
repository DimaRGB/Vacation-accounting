package com.github.dmytr0.vacation.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDate;

@Data
@Entity(name = "employmentInfo")
@NoArgsConstructor
public class EmploymentInfo {

    private String _id;
    private String userName;
    private LocalDate employmentDate;

    public EmploymentInfo(String userName, LocalDate employmentDate) {
        this.userName = userName;
        this.employmentDate = employmentDate;
    }
}
