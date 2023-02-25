package com.microservice.lab.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "calender")
public class Calender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date")
    private Date date;
    @Column(name = "maker")
    private String maker;
    @Column(name = "is_holiday")
    private Boolean isHoliday;
    @ManyToOne
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "school_id")
    private School schoolId;
}
