package com.quicktutorial.learnmicroservices.accountMicroservices.config;

import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountMicroservicesConfiguration {

    @Bean
    public BasicTextEncryptor textEncryptor(){
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword("mySecretEncriptionKeyBlaBla1234");
        return textEncryptor;
    }
}
