package com.quicktutorial.learnmicroservices.accountMicroservices.rest.controller.user.delegate.impl;

import com.quicktutorial.learnmicroservices.accountMicroservices.common.exceptions.UserNotLoggedException;
import com.quicktutorial.learnmicroservices.accountMicroservices.repository.UserRepository;
import com.quicktutorial.learnmicroservices.accountMicroservices.repository.entities.User;
import com.quicktutorial.learnmicroservices.accountMicroservices.rest.controller.user.delegate.LoginDelegate;
import com.quicktutorial.learnmicroservices.accountMicroservices.utils.EncryptionUtils;
import com.quicktutorial.learnmicroservices.accountMicroservices.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.InvalidParameterException;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class LoginDelegateImpl implements LoginDelegate {

    @Autowired
    UserRepository repository;

    @Autowired
    EncryptionUtils encryptionUtils;

    @Override
    public User getUserFromDbAndVerifyPassword(String id, String password) throws InvalidParameterException, UserNotLoggedException{

        if(password == null){
            throw new InvalidParameterException("Type password not found!");
        }

        Optional<User> userr = repository.findById(id);
        if(userr.isPresent()){
            User user = userr.get();
            if(encryptionUtils.decrypt(user.getPassword()).equals(password)){
                log.info("Username and Password verified");
            }else{
                log.info("Username verified. Password not");
                throw new UserNotLoggedException("User not correctly logged in");
            }
        }
        return userr.get();
    }


    @Override
    public String createJwt(String subject, String name, String permission, Date datenow) throws UnsupportedEncodingException {
        Date expDate = datenow;
        expDate.setTime(datenow.getTime() + (300*1000));
        log.info("JWT Creation. Expiration time: " + expDate.getTime());
        String token = JwtUtils.generateJwt(subject, expDate, name, permission);
        log.info("JWT token [{}]. Expiration time: [{}]", token, expDate.getTime());
        return token;
    }


    @Override
    public Map<String, Object> verifyJwtAndGetData(HttpServletRequest request) throws UserNotLoggedException, UnsupportedEncodingException{
        String jwt = JwtUtils.getJwtFromHttpRequest(request);
        if(jwt == null){
            throw new UserNotLoggedException("Authentication token not found in the request");
        }
        Map<String, Object> userData = JwtUtils.jwt2Map(jwt);
        return userData;
    }

}
