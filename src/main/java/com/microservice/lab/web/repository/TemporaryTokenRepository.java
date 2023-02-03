package com.microservice.lab.web.repository;

import com.microservice.lab.web.model.TokenTemporary;
import com.microservice.lab.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TemporaryTokenRepository extends JpaRepository<TokenTemporary, Integer> {
    Optional<TokenTemporary> findByToken(String token);
    Optional<TokenTemporary> findByUser(User user);
}
