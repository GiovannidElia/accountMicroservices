package com.quicktutorial.learnmicroservices.accountMicroservices.rest.controllers.account.delegate;

import com.quicktutorial.learnmicroservices.accountMicroservices.rest.controllers.account.model.response.body.AccountDetailResponseBody;

public interface AccountDetailDelegate {

    AccountDetailResponseBody getAccountDetail(String account);
}
