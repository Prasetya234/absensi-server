package com.microservice.lab.web.controller;


import com.microservice.lab.configuration.exception.BussinesException;
import com.microservice.lab.configuration.response.CommonResponse;
import com.microservice.lab.configuration.response.CommonResponseErr;
import com.microservice.lab.configuration.response.ResponseHelper;
import com.microservice.lab.web.dto.LoginRequest;
import com.microservice.lab.web.dto.RegisterRequest;
import com.microservice.lab.web.model.TokenTemporary;
import com.microservice.lab.web.model.User;
import com.microservice.lab.web.repository.UserRepository;
import com.microservice.lab.web.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class AuthenticationController {

    private AuthenticationService authenticationService;

    private UserRepository userRepository;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService, UserRepository userRepository) {
        this.authenticationService = authenticationService;
        this.userRepository = userRepository;
    }
    @PostMapping("/login")
    public CommonResponse<TokenTemporary> login(@RequestBody LoginRequest loginRequest) {
        return ResponseHelper.ok(authenticationService.login(loginRequest));
    }
    @PostMapping("/register")
    public CommonResponse<User> register(@RequestBody RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new BussinesException("Email already exists!");
        }
        return ResponseHelper.ok(authenticationService.register(registerRequest));
    }
}
