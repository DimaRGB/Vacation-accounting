package com.github.dmytr0.vacation.repository;

import com.github.dmytr0.vacation.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersRepository extends MongoRepository<User, String> {
    User findByEmail(String s);
}
