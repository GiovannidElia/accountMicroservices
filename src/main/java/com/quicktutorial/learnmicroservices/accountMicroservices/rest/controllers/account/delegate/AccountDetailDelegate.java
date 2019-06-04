package com.quicktutorial.learnmicroservices.accountMicroservices.rest.controllers.account.delegate;

import com.quicktutorial.learnmicroservices.accountMicroservices.rest.controllers.account.model.response.AccountDetailResponse;

import java.util.List;

public interface AccountDetailDelegate {

    List<AccountDetailResponse> getAccountDetail(String userCode);
}
