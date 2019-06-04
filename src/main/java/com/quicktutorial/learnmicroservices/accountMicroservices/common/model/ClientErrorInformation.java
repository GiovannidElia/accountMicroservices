package com.quicktutorial.learnmicroservices.accountMicroservices.common.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ClientErrorInformation implements Serializable {

    @JsonProperty
    int errorCode;
    @JsonProperty
    String errorDescription;
}
