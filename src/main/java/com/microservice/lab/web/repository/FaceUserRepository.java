package com.microservice.lab.web.repository;

import com.microservice.lab.web.model.FaceUser;
import com.microservice.lab.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FaceUserRepository extends JpaRepository<FaceUser, String> {
    Optional<FaceUser> findByUserId(User user);
}
