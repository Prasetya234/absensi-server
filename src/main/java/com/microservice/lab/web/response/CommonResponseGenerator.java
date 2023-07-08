package com.microservice.lab.web.response;

import com.microservice.lab.configuration.response.CommonResponseErr;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CommonResponseGenerator {
    public static <T> CommonResponse<T> responseSukses(T data) {
        CommonResponse<T> commonResponse = new CommonResponse<>();
        commonResponse.setStatus(HttpStatus.OK.value());
        commonResponse.setMessage(HttpStatus.OK.name());
        commonResponse.setData(data);
        return commonResponse;
    }


    public static <T> ResponseEntity<?> responseError(T message, HttpStatus http) {
        CommonResponse<T> response = new CommonResponse<>();
        response.setStatus(http.value());
        response.setMessage(http.name());
        response.setData(message);
        return new ResponseEntity<>(response, http);
    }
}
