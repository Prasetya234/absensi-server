package com.microservice.lab.web.repository;

import com.microservice.lab.web.model.School;
import com.microservice.lab.web.model.Presensi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PresensiRepository extends JpaRepository<Presensi, String> {
    List<Presensi> findAllBySchoolId(School school);
    List<Presensi> findAllByIsLate(boolean isLate);
    @Query(value = "SELECT * FROM presensi WHERE CAST(date_submit AS DATE) = ?1 AND user_id = ?2", nativeQuery = true)
    Optional<Presensi> findByDateSubmit(String dateSubmit, String userId);


}
