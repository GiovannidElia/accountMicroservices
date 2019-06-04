package com.quicktutorial.learnmicroservices.accountMicroservices.common.utility;

public enum DatePatternType {
    REQUEST_DATE_FORMAT("ddMMyyyy"),
    REQUEST_MONTH_FORMAT("MMyyyy"),
    DATE_YYYY_MM_DD("yyyy-MM-dd"),
    DATE_DD_MM_YYYY("dd-MM-yyyy"),
    DATE_DD_MM_YYYY_HH_MM_SS("dd-MM-yyyy HH:mm:ss"),
    DATA_YYYY_MM_DD_HH_MM_SS_SSS("yyyy-MM-dd HH:mm:ss.SSS"),
    DATA_YYYY_MM_DDHH_MM_SS_SSS("yyyy-MM-ddHH:mm:ss.SSS"),
    DATA_YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss"),
    TIMESTAMP_YYYY_MM_DD_T_HH_MM_SS("yyyy-MM-dd'T'HH:mm:ss"),
    TIMESTAMP_YYYYMMDD_T_HH_MM_SS("yyyyMMdd'T'HH:mm:ss"),
    TIMESTAMP_YYYYMMDD_T_HH_MM_SS_SSS("yyyyMMdd'T'HH:mm:ss.SSS"),
    TIMESTAMP_YYYYMMDD_T_HHMMSSS("yyyyMMdd'T'HHmmssSSS"),
    TIMESTAMP_YYYYMMDD_T_HHMMSSSSS("yyyyMMdd'T'HHmmssSSS"),
    TIME_HH_MM("HH:mm"),
    TIME_HH_MM_SS("HH:mm:ss"),
    TIME_HHMMSS("HHmmss"),
    DATE_YYYYMMDD("yyyyMMdd"),
    TIME_HHMMSSSS("HHmmssSS"),
    TIME_HHMMSSSSS("HHmmssSSS"),
    TIMESTAMP_YYYYMMDD_HH_MM_SS_SS("yyyyMMdd HH:mm:ss.SS");

    public String pattern = null;

    private DatePatternType(String pattern) {
        this.pattern = pattern;
    }
}

