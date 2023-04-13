package com.microservice.lab.web.dto;

import com.microservice.lab.web.model.OperationalClass;
import lombok.Data;

@Data
public class SchoolDTO {
    private String name;
    private String address;
    private String avatarUrl;
    private String leadInstructor;
    private String foundation;
    private String numberPhone;
    private Integer totalStudent;
    private String backgroundProfile;
    private OperationalClass operationalClass;
}
