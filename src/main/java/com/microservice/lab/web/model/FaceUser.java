package com.microservice.lab.web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.microservice.lab.configuration.auditable.DateConfig;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;

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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User userId;

    @Transient
    private ArrayList<Float> detectorScores = new ArrayList<>();
}
