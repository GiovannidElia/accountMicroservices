package com.quicktutorial.learnmicroservices.accountMicroservices.rest.controller;

import com.quicktutorial.learnmicroservices.accountMicroservices.repository.AccountRepository;
import com.quicktutorial.learnmicroservices.accountMicroservices.repository.OperationRepository;
import com.quicktutorial.learnmicroservices.accountMicroservices.repository.UserRepository;
import com.quicktutorial.learnmicroservices.accountMicroservices.repository.entities.Account;
import com.quicktutorial.learnmicroservices.accountMicroservices.repository.entities.Operation;
import com.quicktutorial.learnmicroservices.accountMicroservices.repository.entities.User;
import com.quicktutorial.learnmicroservices.accountMicroservices.rest.controllers.account.AccountDetailController;
import com.quicktutorial.learnmicroservices.accountMicroservices.utils.EncryptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.InvalidParameterException;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AccountDetailControllerTest {
    private static AccountDetailControllerTest ourInstance = new AccountDetailControllerTest();

    public static AccountDetailControllerTest getInstance() {
        return ourInstance;
    }

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    OperationRepository operationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EncryptionUtils encryptionUtils;

    @Autowired
    AccountDetailController controller;

    @Before
    public void init() throws Exception {

        String encryptedPwd = encryptionUtils.encrypt("Abba");
        System.out.println("Ecripted pwd into DB: " + encryptedPwd);
        log.info("Ecripted pwd into DB: " + encryptedPwd);
        userRepository.save(new User("RGNLSN87H13D761R", "Alessandro Argentieri", encryptedPwd, "user"));

        encryptedPwd = encryptionUtils.encrypt("WeLoveTokyo");
        userRepository.save(new User("FRNFBA85M08D761M", "Fabio Fiorenza", encryptedPwd, "user"));

        encryptedPwd = encryptionUtils.encrypt("Melograno");
        userRepository.save(new User("DSTLCU89R52D761R", "Lucia Distante", encryptedPwd, "user"));

        accountRepository.save(new Account("cn4563df3", "RGNLSN87H13D761R", 3000.00));
        accountRepository.save(new Account("cn7256su9", "RGNLSN87H13D761R", 4000.00));
        accountRepository.save(new Account("cn6396dr7", "FRNFBA85M08D761M", 7000.00));
        accountRepository.save(new Account("cn2759ds4", "DSTLCU89R52D761R", 2000.00));
        accountRepository.save(new Account("cn2874da2", "DSTLCU89R52D761R", 8000.00));

        operationRepository.save(new Operation("3452",new Date(),"Bonifico bancario", 100.00, "cn4563df3","cn4563df3"));
        operationRepository.save(new Operation("3453",new Date(),"Pagamento tasse", -100.00, "cn4563df3","cn4563df3"));
        operationRepository.save(new Operation("3454",new Date(),"Postagiro", 230.00, "cn4563df3","cn2759ds4"));
        operationRepository.save(new Operation("3455",new Date(),"Vaglia postale", 172.00, "cn4563df3","cn4563df3"));
        operationRepository.save(new Operation("3456",new Date(),"Acquisto azioni", -3400.00, "cn2759ds4",""));
        operationRepository.save(new Operation("3457",new Date(),"Vendita azione", 100.00, "cn4563df3",""));
        operationRepository.save(new Operation("3458",new Date(),"Prelevamento", -100.00, "cn4563df3",""));
        operationRepository.save(new Operation("3459",new Date(),"Deposito", 1100.00, "cn4563df3",""));
        operationRepository.save(new Operation("3460",new Date(),"Bonifico bancario", 100.00, "cn2874da2","cn4563df3"));
        operationRepository.save(new Operation("3461",new Date(),"Bonifico bancario", 100.00, "cn4563df3","cn2874da2"));
        operationRepository.save(new Operation("3462",new Date(),"Bonifico bancario", 100.00, "cn4563df3","cn4563df3"));
        operationRepository.save(new Operation("3463",new Date(),"Postagiro", 230.00, "cn7256su9","cn2759ds4"));
        operationRepository.save(new Operation("3464",new Date(),"Vaglia postale", 172.00, "cn4563df3","cn7256su9"));
        operationRepository.save(new Operation("3465",new Date(),"Acquisto azioni", -3400.00, "cn7256su9",""));
    }

    @Test
    public void validateRequestTypeAndDate() throws Exception {

        try {
            controller.accountDetail("RGNLSN87H13D761R");
        } catch (InvalidParameterException e) {
            log.debug("ERROR thrown {} ", e.getMessage(), e);
            if (e.getMessage().toUpperCase().contains("DATE")) {
                throw e;
            }
        }
    }
}
