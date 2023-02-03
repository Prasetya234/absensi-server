package com.microservice.lab.configuration.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping
public class HomeController {
    @GetMapping
    public String home() {
        return "Server lab runing at " + new Date();
    }
}
