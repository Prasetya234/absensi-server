package com.microservice.lab.web.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "token_temporary")
public class TokenTemporary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "token")
    private String token;
    @Column(name = "validity_period")
    private String validityPeriod;
    @Column(name =  "expired_date")
    private Date expiredDate;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
