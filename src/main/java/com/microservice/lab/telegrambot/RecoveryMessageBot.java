package com.microservice.lab.telegrambot;


import com.microservice.lab.configuration.data.TelegramMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Date;

@Component
public class RecoveryMessageBot {
    public void sendMessage(Date date, String ipAddress, String url, String method, Object payload, String user, String token) throws UnsupportedEncodingException {
        String mainUrl = "https://api.telegram.org/bot5458201499:AAFxB77rGvyATgnMnIOxeAU7w7SnlBqyLSg/sendMessage?chat_id=@absensierrorstaging&text=";
        String param = "";
        TelegramMessage telegramMessage = TelegramMessage.builder().date(new Date()).remoteAddr(ipAddress).url(url).method(method).payload(payload).user(user).token(token).build();
        param += "timestamp: " + telegramMessage.getDate();
        param += '\n';
        param += "remote_addr: " + telegramMessage.getRemoteAddr();
        param += '\n';
        param += "user: " + telegramMessage.getUser();
        param += '\n';
        param += "method: " + telegramMessage.getMethod();
        param += '\n';
        param += "payload: " + telegramMessage.getPayload();
        param += '\n';
        param += "token: " + telegramMessage.getToken();
        param += '\n';
        param += "url: " + telegramMessage.getUrl();
        final String uri = mainUrl+param;
        RestTemplate restTemplate = new RestTemplate();
        final MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM));
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
        restTemplate.getForObject(uri, String.class);
    }
}
