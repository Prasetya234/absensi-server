package com.microservice.lab.web.repository;

import com.microservice.lab.web.model.GoingHomeEarly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoingHomeEarlyRepository extends JpaRepository<GoingHomeEarly, Integer> {
}
