package com.microservice.lab.web.controller;


import com.microservice.lab.configuration.response.CommonResponse;
import com.microservice.lab.configuration.response.ResponseHelper;
import com.microservice.lab.web.dto.LoginRequest;
import com.microservice.lab.web.dto.RegisterRequest;
import com.microservice.lab.web.model.TokenTemporary;
import com.microservice.lab.web.model.User;
import com.microservice.lab.web.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class AuthenticationController {

    private AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
    @PostMapping("/login")
    public CommonResponse<TokenTemporary> login(@RequestBody LoginRequest loginRequest) {
        return ResponseHelper.ok(authenticationService.login(loginRequest));
    }
    @PostMapping("/register")
    public CommonResponse<User> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseHelper.ok(authenticationService.register(registerRequest));
    }
}
