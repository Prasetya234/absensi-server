package com.microservice.lab.web.model;

import com.microservice.lab.web.model.message.Status;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "messages")
@NoArgsConstructor
@AllArgsConstructor
public class Messages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "sender_name")
    private String senderName;
    @Column(name = "receiver_name")
    private String receiverName;
    @Lob
    @Column(name = "message")
    private String message;
    @Column(name = "date")
    private String date;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "school_id")
    private String schoolId;
}
