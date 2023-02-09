package com.microservice.lab.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.microservice.lab.configuration.auditable.DateConfig;
import com.microservice.lab.web.enumated.GenderEnum;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User extends DateConfig {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @Column(name = "no_siswa")
    private Integer noSiswa;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @JsonIgnore
    @Column(name = "password")
    private String password;
    @Column(name = "avatar_url")
    private String avatarUrl;
    @Column(name = "school_class")
    private String schoolClass;
    @Column(name = "batch")
    private Integer batch;
    @Lob
    @Column(name = "description")
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(name="gender")
    private GenderEnum gender;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "birth_date")
    private Date birthDate;
    @Column(name = "favorite")
    private Boolean favorite;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role roleId;
    @ManyToOne
    @JoinColumn(name = "class_bootcamp_id")
    private ClassBootcamp classBootcampId;
}
