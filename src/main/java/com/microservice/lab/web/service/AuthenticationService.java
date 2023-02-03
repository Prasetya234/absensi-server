package com.microservice.lab.web.service;

import com.microservice.lab.web.dto.LoginRequest;
import com.microservice.lab.web.dto.RegisterRequest;
import com.microservice.lab.web.model.TokenTemporary;
import com.microservice.lab.web.model.User;

public interface AuthenticationService {
    TokenTemporary login(LoginRequest loginRequest);
    User register(RegisterRequest registerRequest);
}
