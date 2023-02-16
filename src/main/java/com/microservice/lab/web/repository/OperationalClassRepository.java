package com.microservice.lab.web.repository;

import com.microservice.lab.web.model.OperationalClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationalClassRepository extends JpaRepository<OperationalClass, Integer> {
}
