package com.quicktutorial.learnmicroservices.accountMicroservices.daos;

import com.quicktutorial.learnmicroservices.accountMicroservices.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, String> {

    Optional<User> findById(String id);
}
