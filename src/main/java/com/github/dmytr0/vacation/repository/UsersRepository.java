package com.github.dmytr0.vacation.repository;

import com.github.dmytr0.vacation.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsersRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String s);
}
