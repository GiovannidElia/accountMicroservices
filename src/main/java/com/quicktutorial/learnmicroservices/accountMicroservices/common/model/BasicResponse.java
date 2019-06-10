package com.quicktutorial.learnmicroservices.accountMicroservices.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

import static com.quicktutorial.learnmicroservices.accountMicroservices.common.Constant.JSON_TIMESTAMP_FORMAT;

@ToString
public class BasicResponse<T> implements Serializable {

    @Getter @Setter
    private T data;

    @Getter @Setter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = JSON_TIMESTAMP_FORMAT)
    private LocalDateTime timestamp;


}
