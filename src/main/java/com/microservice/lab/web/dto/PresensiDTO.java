package com.microservice.lab.web.dto;

import com.microservice.lab.web.model.Reason;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
public class PresensiDTO {
    private String faceNumber;
    private String latitude;
    private String longitude;
    private Boolean isLate;
    private Boolean permissionAttend;
    private String note;
    private Date dateSubmit;
    private String reason;
}
