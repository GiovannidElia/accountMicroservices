package com.quicktutorial.learnmicroservices.accountMicroservices.common.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString(callSuper = true)
public class ExtendedResponse<T> extends BasicResponse<T> implements Serializable {

    private SourceUpdate lastUpdates;

}
