package com.microservice.lab.web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.microservice.lab.configuration.auditable.DateConfig;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "face_user")
public class FaceUser extends DateConfig {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Lob
    @JsonIgnore
    @Column(name = "detector_score")
    private String detectorScore;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User userId;

    @Transient
    private List<String> detectorScores = new ArrayList<>();
}
