package com.quicktutorial.learnmicroservices.accountMicroservices.rest.controller.account.delegate;

import com.quicktutorial.learnmicroservices.accountMicroservices.common.exceptions.NoDataFoundException;
import com.quicktutorial.learnmicroservices.accountMicroservices.rest.controller.account.exceptions.AccountDetailException;
import com.quicktutorial.learnmicroservices.accountMicroservices.rest.controller.account.model.response.AccountDetailResponse;

import java.security.InvalidParameterException;
import java.util.List;

public interface AccountDetailDelegate {

    List<AccountDetailResponse> getAccountDetail(String userCode) throws InvalidParameterException, NoDataFoundException, AccountDetailException;
}
