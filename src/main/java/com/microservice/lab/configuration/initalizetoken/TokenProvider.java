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
        Optional<TokenTemporary> data = temporaryTokenRepository.findByUser(user);
        if (data.isPresent()) temporaryTokenRepository.delete(data.get());
        return temporaryTokenRepository.save(TokenTemporary.builder()
                .token(generateRandomToken())
                .validityPeriod("30 Minute")
                .user(user)
                .expiredDate(new Date((new Date()).getTime() + TOKEN_EXPIRE)).build());
    }

    public TokenTemporary generateDataToken(String token) {
        return  temporaryTokenRepository.findByToken(token).orElseThrow(() -> new NotFoundException("TOKEN NOT FOUND"));
    }

    public TokenTemporary reverseToken(String token) {
        TokenTemporary data = generateDataToken(token);
        data.setExpiredDate(new Date((new Date()).getTime() + TOKEN_EXPIRE));
        return temporaryTokenRepository.save(data);
    }

    public boolean validateJwtToken(String token) {
      TokenTemporary tokenUser =  temporaryTokenRepository.findByToken(token).orElseThrow(() -> new NotFoundException("TOKEN NOT FOUND"));
      if (tokenUser.getExpiredDate().before(new Date())) {
          return  false;
      }
      return  true;
    }

    private static String generateRandomToken() {
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@#$%&";
        Random rnd = new Random();
        int len = 150;
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        return sb.toString();
    }
}
