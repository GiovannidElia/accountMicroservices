package com.quicktutorial.learnmicroservices.accountMicroservices.rest.controllers.account.model.response.header;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.quicktutorial.learnmicroservices.accountMicroservices.common.utility.Constant;
import com.quicktutorial.learnmicroservices.accountMicroservices.rest.controllers.account.model.response.body.AccountDetailResponseBody;
import lombok.Setter;

public class AccountDetailResponseObject {

    @Setter
    @JsonProperty(value = "typeOp")
    private String typeOp;		//Type operation call

    @Setter
    @JsonProperty(value = "statusOp")
    private Constant.RESPONSE_STATUS statusOp;	//Status operation call { OK | KO }

    @Setter
    @JsonProperty(value = "msgOp")
    private String msgOp;

    @Setter
    @JsonProperty(value = "value")
    private AccountDetailResponseBody value;
}
