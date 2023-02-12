package com.microservice.lab.web.repository;

import com.microservice.lab.web.model.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MassagesRepository extends JpaRepository<Messages, Integer> {
    List<Messages> findAllBySchoolId(String schoolId);
}
