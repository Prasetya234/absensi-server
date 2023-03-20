package com.microservice.lab.web.repository;

import com.microservice.lab.web.model.School;
import com.microservice.lab.web.model.Presensi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PresensiRepository extends JpaRepository<Presensi, String> {
    Page<Presensi> findAllBySchoolId(School school, Pageable pageable);

    @Query(value = "SELECT * FROM presensi p JOIN user u ON p.user_id = u.id WHERE u.first_name LIKE CONCAT('%', ?1, '%') AND p.is_late = ?2", nativeQuery = true)
    Page<Presensi> findAllByIsLate(String keyword, boolean isLate, Pageable pageable);

    @Query(value = "SELECT * FROM presensi WHERE CAST(date_submit AS DATE) = ?1 AND user_id = ?2", nativeQuery = true)
    Optional<Presensi> findByDateSubmit(String dateSubmit, String userId);
}
