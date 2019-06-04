package com.quicktutorial.learnmicroservices.accountMicroservices.rest.controllers.account.delegate.impl;

import com.quicktutorial.learnmicroservices.accountMicroservices.repository.AccountRepository;
import com.quicktutorial.learnmicroservices.accountMicroservices.repository.entities.Account;
import com.quicktutorial.learnmicroservices.accountMicroservices.rest.controllers.account.delegate.AccountDetailDelegate;
import com.quicktutorial.learnmicroservices.accountMicroservices.rest.controllers.account.model.response.AccountDetailResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class AccountDetailDelegateImpl implements AccountDetailDelegate {

    @Autowired
    AccountRepository repository;

    @Override
    public List<AccountDetailResponse> getAccountDetail(String userCode) {
        log.debug("Into getAccountDetail delegate with PathParameter [{}]", userCode);

        List<Account> dbResult = repository.getAllAccountPerUser(userCode);
        List<AccountDetailResponse> response = dbResultToDto(dbResult);

        return response;
    }

    private List<AccountDetailResponse> dbResultToDto(List<Account> dtos) {
        List<AccountDetailResponse> formattedDTOs = new ArrayList<>();
        for (Account dto : dtos) {
            AccountDetailResponse fileDto = new AccountDetailResponse();
            fileDto.setId(dto.getId());
            fileDto.setFkUser(dto.getFkUser());
            fileDto.setTotal(BigDecimal.valueOf(dto.getTotal()).setScale(2,BigDecimal.ROUND_HALF_DOWN));
            formattedDTOs.add(fileDto);
        }
        return formattedDTOs;
    }

}
