package com.quicktutorial.learnmicroservices.accountMicroservices.rest.controller.account.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class AccountDetailRequest {

    @Getter
    @Setter
    private String cf;

}
