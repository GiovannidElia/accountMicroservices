package com.quicktutorial.learnmicroservices.accountMicroservices.rest.controllers.account.model.response.body;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.quicktutorial.learnmicroservices.accountMicroservices.rest.controllers.account.model.response.dto.AccountDetailDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class AccountDetailResponseBody implements Serializable {

    @Getter @Setter
    @JsonProperty(value = "filter")
    private Object filter;

    @Getter @Setter
    @JsonProperty(value = "total")
    private long total;

    @Getter @Setter
    @JsonProperty(value = "data")
    private List<AccountDetailDTO> data;

}
