package com.microservice.lab.web.dto;

import lombok.Data;

@Data
public class ToDoListDTO {
    private String note;
    private Boolean status;
}
