package com.microservice.lab.web.service;

import com.microservice.lab.web.model.Messages;

import java.util.List;

public interface MessagesService {
    String send(Messages messages);
    List<Messages> messages();
}
