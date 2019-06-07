package com.quicktutorial.learnmicroservices.accountMicroservices.rest.controller.account.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
public class AccountDetailResponse {

    @Getter
    @Setter
    private String id;

    @Getter @Setter
    private String fkUser;

    @Getter @Setter
    private BigDecimal total;
}
