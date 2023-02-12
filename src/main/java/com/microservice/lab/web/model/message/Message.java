package com.microservice.lab.web.model.message;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Message {
    private String senderName;
    private String receiverName;
    private String schoolId;
    private String message;
    private String date;
    private Status status;
}
