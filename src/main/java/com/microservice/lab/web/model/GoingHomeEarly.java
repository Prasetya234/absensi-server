package com.microservice.lab.web.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.microservice.lab.configuration.auditable.DateConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "going_home_early")
public class GoingHomeEarly extends DateConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Lob
    @Column(name = "note")
    private String note;
    @Column(name = "date")
    private Date date;
    @Column(name = "mark_by")
    private String markBy;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "class_bootcamp_id")
    private School schoolId;
}
