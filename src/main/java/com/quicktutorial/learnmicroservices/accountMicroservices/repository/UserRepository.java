package com.quicktutorial.learnmicroservices.accountMicroservices.repository;

import com.quicktutorial.learnmicroservices.accountMicroservices.repository.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findById(String id);
}
