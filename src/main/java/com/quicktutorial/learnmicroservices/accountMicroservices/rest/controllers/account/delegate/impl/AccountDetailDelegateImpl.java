package com.quicktutorial.learnmicroservices.accountMicroservices.rest.controllers.account.delegate.impl;

import com.quicktutorial.learnmicroservices.accountMicroservices.repository.AccountRepository;
import com.quicktutorial.learnmicroservices.accountMicroservices.repository.entities.Account;
import com.quicktutorial.learnmicroservices.accountMicroservices.rest.controllers.account.delegate.AccountDetailDelegate;
import com.quicktutorial.learnmicroservices.accountMicroservices.rest.controllers.account.model.response.body.AccountDetailResponseBody;
import com.quicktutorial.learnmicroservices.accountMicroservices.rest.controllers.account.model.response.dto.AccountDetailDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class AccountDetailDelegateImpl implements AccountDetailDelegate {

    @Autowired
    AccountRepository repository;

    @Override
    public AccountDetailResponseBody getAccountDetail(String account) {
        log.debug("Into getAccountDetail delegate with PathParameter [{}]", account);

        List<Account> dbResult;

        dbResult = repository.getAllAccountPerUser(account);
        AccountDetailResponseBody response = new AccountDetailResponseBody();
        response.setFilter(account);
        response.setTotal(0);
        response.setData(dbResultToDto(dbResult));

        return response;
    }

    private List<AccountDetailDTO> dbResultToDto(List<Account> dtos) {
        List<AccountDetailDTO> formattedDTOs = new ArrayList<>();
        for (Account dto : dtos) {
            AccountDetailDTO fileDto = new AccountDetailDTO();
            fileDto.setId(dto.getId());
            fileDto.setFkUser(dto.getFkUser());
            fileDto.setTotal(Double.valueOf(dto.getTotal()));
            formattedDTOs.add(fileDto);
        }
        return formattedDTOs;
    }

}
