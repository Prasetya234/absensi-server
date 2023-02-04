package com.microservice.lab.web.dto;

import com.microservice.lab.web.enumated.LoginTypeEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
public class LoginRequest {
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private LoginTypeEnum type;
}
