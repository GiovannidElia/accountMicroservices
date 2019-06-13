package com.quicktutorial.learnmicroservices.accountMicroservices.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Date;

@Aspect
@Order(10)
@Configuration
@Slf4j
public class TimestampAspectAdvice {

    @AfterReturning(pointcut = "execution(public * com.quicktutorial.learnmicroservices.accountMicroservices.rest.controller.account.AccountDetailController.*(..))", returning = "returnValue")
    public void setTimestamp(JoinPoint joinPoint, Object returnValue) throws IllegalAccessException, SecurityException, IllegalArgumentException, InvocationTargetException {
        log.debug("Entering setTimestamp aspect for method [{}]", joinPoint.getSignature());

        try {
            if (returnValue != null && ((ResponseEntity) returnValue).getBody()!=null) {
                Class clazz = ((ResponseEntity) returnValue).getBody().getClass();
                Method method;
                if (clazz != null) {
                    try {
                        method = clazz.getMethod("setTimestamp", LocalDateTime.class);
                        method.invoke(((ResponseEntity) returnValue).getBody(), LocalDateTime.now());
                    } catch (NoSuchMethodException e) {
                        log.debug("La response non prevede un metodo setTimestamp");
                    }
                }

            }
        } catch (Exception e) {
            log.error("errore", e);
            throw e;
        }
    }

    private Date getTimestamp() {
        return new Date();
    }

}
