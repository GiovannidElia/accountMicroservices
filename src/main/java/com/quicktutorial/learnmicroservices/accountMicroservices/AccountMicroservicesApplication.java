package com.quicktutorial.learnmicroservices.accountMicroservices;

import com.quicktutorial.learnmicroservices.accountMicroservices.repository.AccountRepository;
import com.quicktutorial.learnmicroservices.accountMicroservices.repository.OperationRepository;
import com.quicktutorial.learnmicroservices.accountMicroservices.repository.UserRepository;
import com.quicktutorial.learnmicroservices.accountMicroservices.repository.entities.Account;
import com.quicktutorial.learnmicroservices.accountMicroservices.repository.entities.Operation;
import com.quicktutorial.learnmicroservices.accountMicroservices.repository.entities.User;
import com.quicktutorial.learnmicroservices.accountMicroservices.utils.EncryptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
@Slf4j
public class AccountMicroservicesApplication implements CommandLineRunner {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	OperationRepository operationRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	EncryptionUtils encryptionUtils;

	public static void main(String[] args) {
		SpringApplication.run(AccountMicroservicesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Hello 1");


	}

}
