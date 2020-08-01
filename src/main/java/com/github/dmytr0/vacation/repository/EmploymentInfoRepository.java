package com.github.dmytr0.vacation.repository;

import com.github.dmytr0.vacation.domain.EmploymentInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EmploymentInfoRepository extends MongoRepository<EmploymentInfo, String> {

    Optional<EmploymentInfo> findByUserName(String userName);
}
