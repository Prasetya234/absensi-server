package com.microservice.lab.web.model;

import com.microservice.lab.configuration.auditable.DateConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "operational_class")
public class OperationalClass extends DateConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "entry_time")
    private String entryTime;
    @Column(name = "dismissal_time")
    private String dismissalTime;
    @Column(name = "duration_learn")
    private Integer durationLearn;
    @Column(name = "admission_day")
    private Integer admissionDay;
    @Column(name = "mark_by")
    private String markBy;
}
