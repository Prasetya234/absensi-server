package com.microservice.lab.web.repository;

import com.microservice.lab.web.model.Reason;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReasonRepository extends JpaRepository<Reason, Integer> {
    @Query(value = "SELECT * FROM reason r WHERE r.name = ?1", nativeQuery = true)
    Optional<Reason> findByName(String name);
}
