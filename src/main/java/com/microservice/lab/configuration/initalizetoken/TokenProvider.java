package com.microservice.lab.configuration.initalizetoken;

import com.microservice.lab.configuration.exception.NotFoundException;
import com.microservice.lab.web.model.TokenTemporary;
import com.microservice.lab.web.model.User;
import com.microservice.lab.web.repository.TemporaryTokenRepository;
import com.microservice.lab.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;
import java.util.Random;

import static com.microservice.lab.utils.Data.REVERSE_TOKEN_EXPIRE;
import static com.microservice.lab.utils.Data.TOKEN_EXPIRE;

@Component
public class TokenProvider {
    private TemporaryTokenRepository temporaryTokenRepository;
    private UserRepository userRepository;

    @Autowired
    public TokenProvider(TemporaryTokenRepository temporaryTokenRepository, UserRepository userRepository) {
        this.temporaryTokenRepository = temporaryTokenRepository;
        this.userRepository = userRepository;
    }

    public TokenTemporary generateToken(UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername()).get();
        return temporaryTokenRepository.save(TokenTemporary.builder()
                .token(generateRandomToken())
                .validityPeriod("Infinity")
                .expired(false)
                .user(user).build());
    }

    public boolean deleteToken(String token) {
        TokenTemporary tkn =  temporaryTokenRepository.findByToken(token).orElseThrow(() -> new NotFoundException("TOKEN NOT FOUND"));
        tkn.setExpired(true);
        temporaryTokenRepository.save(tkn);
        return true;

    }

    public TokenTemporary generateDataToken(String token) {
        return  temporaryTokenRepository.findByToken(token).orElseThrow(() -> new NotFoundException("TOKEN NOT FOUND"));
    }

    public TokenTemporary reverseToken(String token) {
        TokenTemporary data = generateDataToken(token);
        return temporaryTokenRepository.save(data);
    }

    public boolean validateJwtToken(String token) {
      TokenTemporary tokenUser =  temporaryTokenRepository.findByToken(token).orElseThrow(() -> new NotFoundException("TOKEN NOT FOUND"));
      return !tokenUser.isExpired();
    }

    private static String generateRandomToken() {
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random rnd = new Random();
        int len = 148;
        StringBuilder sb = new StringBuilder(len);
        sb.append("v1_");
        for (int i = 0; i < len; i++)
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        return sb.toString();
    }
}
