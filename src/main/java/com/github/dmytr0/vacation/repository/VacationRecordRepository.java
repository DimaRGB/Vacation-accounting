package com.github.dmytr0.vacation.repository;

import com.github.dmytr0.vacation.domain.VacationRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VacationRecordRepository extends MongoRepository<VacationRecord, String> {

    List<VacationRecord> findByUserName(String userName);
}
