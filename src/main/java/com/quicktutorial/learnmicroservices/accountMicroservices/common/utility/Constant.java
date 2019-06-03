package com.quicktutorial.learnmicroservices.accountMicroservices.common.utility;

public class Constant {

    public enum RESPONSE_STATUS {
        STATUS_OP_OK("OK"),
        STATUS_OP_KO("KO");

        public String status = null;

        private RESPONSE_STATUS(String status) {
            this.status = status;
        }
    }
}
