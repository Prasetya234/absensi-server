package com.microservice.lab.web.repository;

import com.microservice.lab.web.model.ClassBootcamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassBootcampRepository extends JpaRepository<ClassBootcamp, String> {
}
