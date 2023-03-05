package com.microservice.lab.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Builder
@Getter
@Setter
public class DateResponse {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date start;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date end;
}
