package com.quicktutorial.learnmicroservices.accountMicroservices.common.model;

import java.io.Serializable;

public class BasicResponse<T> implements Serializable {

    private T data;

    private String lastUpdate;
    private String timestamp;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BasicResponse{");
        sb.append("data=").append(data);
        sb.append(", lastUpdate='").append(lastUpdate).append('\'');
        sb.append(", timestamp='").append(timestamp).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
