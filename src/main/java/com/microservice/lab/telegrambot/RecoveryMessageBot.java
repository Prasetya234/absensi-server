package com.microservice.lab.telegrambot;


import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Component
public class RecoveryMessageBot {
        public void sendMessage(Date date, String ipAddress, String token, String message) {
            String space = "\n";
            String dateMsg = "Timestamp: " + date.toString();
            String ipAddressMsg = "IpAddress: " + ipAddress;
            String tokenMsg = "Token: " + token;
            String messageMsg = "Message: " + message;
            final String messageFinal =  dateMsg + space + ipAddressMsg + space + tokenMsg + space + messageMsg;
            final String uri = "https://api.telegram.org/bot5458201499:AAFxB77rGvyATgnMnIOxeAU7w7SnlBqyLSg/sendMessage?chat_id=@absensierrorstaging&text=" + messageFinal;
            RestTemplate restTemplate = new RestTemplate();
           restTemplate.getForObject(uri, String.class);
        }
}
