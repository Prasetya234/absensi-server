package com.microservice.lab.web.model;

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
@Table(name = "class_bootcamp")
public class ClassBootcamp extends DateConfig {
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
}
