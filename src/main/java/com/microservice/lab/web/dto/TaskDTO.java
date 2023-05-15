package com.microservice.lab.web.dto;

import com.microservice.lab.web.enumated.TaskTypeEnum;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@Data
public class TaskDTO {
    private String note;
    private Date assignmentDate;
    private Date dueDate;
    private Boolean isClosed;
    private String user;
    @Enumerated(EnumType.STRING)
    private TaskTypeEnum taskType;
}
