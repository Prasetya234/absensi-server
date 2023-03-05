package com.microservice.lab.telegrambot;


import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Date;

@Component
public class RecoveryMessageBot {
    public void sendMessage(Date date, String ipAddress, String token, String message) {
        StringBuilder str = new StringBuilder();
        str.append("https://api.telegram.org/bot5458201499:AAFxB77rGvyATgnMnIOxeAU7w7SnlBqyLSg/sendMessage?chat_id=@absensierrorstaging&text=");
        String space = "\t\t\t\t";
        String dateMsg = "Timestamp:\t" + date.toString();
        String ipAddressMsg = "IpAddress:\t" + ipAddress;
        String tokenMsg = "Token:\t" + token;
        String messageMsg = "Message:\t" + message;
        final String messageFinal = dateMsg + space + ipAddressMsg + space + tokenMsg + space + messageMsg;

        str.append(messageFinal);
        final String uri = str.toString();
        System.out.println(uri);
        RestTemplate restTemplate = new RestTemplate();
        final MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM));
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
        restTemplate.getForObject(uri, String.class);
    }
}
