package com.microservice.lab.web.serviceImpl;

import com.microservice.lab.configuration.data.UserDetailsServiceImpl;
import com.microservice.lab.configuration.exception.BussinesException;
import com.microservice.lab.configuration.exception.NotFoundException;
import com.microservice.lab.configuration.initalizetoken.TokenProvider;
import com.microservice.lab.web.dto.LoginRequest;
import com.microservice.lab.web.dto.RegisterRequest;
import com.microservice.lab.web.model.TokenTemporary;
import com.microservice.lab.web.model.User;
import com.microservice.lab.web.repository.SchoolRepository;
import com.microservice.lab.web.repository.RoleRepository;
import com.microservice.lab.web.repository.UserRepository;
import com.microservice.lab.web.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public  class AuthenticationServiceImpl implements AuthenticationService {
    private UserRepository userRepository;
    private TokenProvider tokenProvider;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    private SchoolRepository schoolRepository;
    private AuthenticationManager authenticationManager;
    private RoleRepository roleRepository;
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public AuthenticationServiceImpl(UserRepository userRepository, TokenProvider tokenProvider, ModelMapper modelMapper, PasswordEncoder passwordEncoder, SchoolRepository schoolRepository, RoleRepository roleRepository, AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService) {
        this.userRepository = userRepository;
        this.tokenProvider = tokenProvider;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.schoolRepository = schoolRepository;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public TokenTemporary login(LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(e.getMessage());
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(() -> new NotFoundException("EMAIL NOT FOUND IN GENERATE TOKEN"));
        if (!user.getRoleId().getName().equals(loginRequest.getType().name())) throw new BussinesException("ACCESS REJECTED");
        return tokenProvider.generateToken(userDetails);
    }

    @Transactional
    @Override
    public User register(RegisterRequest registerRequest) {
        User user = modelMapper.map(registerRequest, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setSchoolId(schoolRepository.findById(registerRequest.getSchoolId()).orElseThrow(() -> new NotFoundException("CLASS BOOTCAMP ID NOT FOUND")));
        user.setRoleId(roleRepository.findById(1).get());
        user.setViewers(0);
        return userRepository.save(user);
    }
}
