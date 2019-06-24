package com.quicktutorial.learnmicroservices.accountMicroservices.repository;

import com.quicktutorial.learnmicroservices.accountMicroservices.repository.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
