package com.microservice.lab.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommonResponse<T> {
    @JsonProperty("status")
    private int status;
    @JsonProperty("message")
    private String message;
    @JsonProperty("data")
    private T data;

    public CommonResponse() {
    }

    // all field setter & getter
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
