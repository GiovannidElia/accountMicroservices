package com.quicktutorial.learnmicroservices.accountMicroservices.common.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@ToString
public class BasicResponse<T> implements Serializable {

    @Getter @Setter
    private T data;

    @Getter @Setter
    private String timestamp;


}
