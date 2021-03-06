package com.quicktutorial.learnmicroservices.accountMicroservices.repository;

import com.quicktutorial.learnmicroservices.accountMicroservices.repository.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, String> {

    @Query(value="SELECT * FROM accounts WHERE FK_USER=:userCode", nativeQuery = true)
    List<Account> getAllAccountPerUser(@Param("userCode")String userCode);

    List<Account> findByFkUser(String fkUser);
}
