package com.microservice.lab.web.dto;

import com.microservice.lab.web.enumated.GenderEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@Getter
@Setter
public class RegisterRequest {
    private int noSiswa;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String description;
    private String avatarUrl;
    private String schoolClass;
    private int batch;
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;
    private Date birthDate;
    private Boolean favorite;
    private String schoolId;
}

