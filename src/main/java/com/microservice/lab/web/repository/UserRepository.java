package com.microservice.lab.web.repository;

import com.microservice.lab.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User getUserById(String id);

    Optional<User> findByEmail(String email);
}
