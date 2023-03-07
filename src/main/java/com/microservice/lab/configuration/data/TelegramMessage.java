package com.microservice.lab.configuration.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Builder
@Getter
@Setter
public class TelegramMessage {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;
    private String remoteAddr;
    private String url;
    private String method;
    private Object payload;
    private String user;
    private String token;
}
