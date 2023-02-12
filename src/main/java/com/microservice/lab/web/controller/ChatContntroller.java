package com.microservice.lab.web.controller;

import com.microservice.lab.configuration.response.CommonResponse;
import com.microservice.lab.configuration.response.ResponseHelper;
import com.microservice.lab.web.model.Messages;
import com.microservice.lab.web.service.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatContntroller {
    private MessagesService messagesService;

    @Autowired
    public ChatContntroller(MessagesService messagesService) {
        this.messagesService = messagesService;
    }

    @PostMapping("/send")
    public CommonResponse<String> send(@RequestBody Messages messages) {
        return ResponseHelper.ok(messagesService.send(messages));
    }

    @GetMapping
    public CommonResponse<List<Messages>> chats() {
        return ResponseHelper.ok(messagesService.messages());
    }
}
