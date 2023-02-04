package com.microservice.lab.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CalenderRequest {
    private String name;
    private Date date;
    private Boolean isHoliday;
}
