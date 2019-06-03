package com.quicktutorial.learnmicroservices.accountMicroservices.rest.controllers.account.model.response.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class AccountDetailDTO {

    @Getter
    @Setter
    private String id;

    @Getter @Setter
    private String fkUser;

    @Getter @Setter
    private Double total;
}
