package com.microservice.lab.web.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.microservice.lab.configuration.auditable.DateConfig;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "school")
public class School extends DateConfig {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @Column(name = "name")
    private String name;
    @Lob
    @Column(name = "address")
    private String address;
    @Column(name = "avatar_url")
    private String avatarUrl;
    @Column(name = "lead_instructor")
    private String leadInstructor;
    @Column(name = "foundation")
    private String foundation;
    @Column(name = "number_phone")
    private String numberPhone;
    @Column(name = "total_student")
    private Integer totalStudent;
    @Lob
    @Column(name = "background_profile")
    private String backgroundProfile;
    @OneToOne(fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "operational_class_id")
    private OperationalClass operationalClass;
    @Transient
    private GoingHomeEarly goingHomeEarly;
}
