package com.microservice.lab.web.repository;

import com.microservice.lab.web.model.School;
import com.microservice.lab.web.model.Presensi;
import com.microservice.lab.web.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PresensiRepository extends JpaRepository<Presensi, String> {

    @Query(value = "SELECT p.* FROM presensi p LEFT JOIN user u ON p.user_id = u.id WHERE u.first_name LIKE CONCAT('%', ?1, '%') AND p.is_late LIKE CONCAT('%', ?2, '%') AND p.class_bootcamp_id LIKE CONCAT('%', ?3, '%')", nativeQuery = true)
    Page<Presensi> findAllByIsLate(String keyword, String isLate, School school, Pageable pageable);

    Long countByUserIdAndIsLate(User userId, Boolean isLate);

    @Query(value = "SELECT * FROM presensi p WHERE p.user_id LIKE CONCAT('%', ?1, '%') AND p.permission_attend = 0", nativeQuery = true)
    Page<Presensi> countPresentByUserId(User userId, Pageable pageable);
    @Query(value = "SELECT * FROM presensi p WHERE p.user_id LIKE CONCAT('%', ?1, '%') AND p.permission_attend = 1", nativeQuery = true)
    Page<Presensi> countPermissionByUserId(User userId, Pageable pageable);

    @Query(value = "SELECT * FROM presensi WHERE CAST(date_submit AS DATE) = ?1 AND user_id = ?2", nativeQuery = true)
    Optional<Presensi> findByDateSubmit(String dateSubmit, String userId);
}
