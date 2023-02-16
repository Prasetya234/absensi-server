package com.microservice.lab.web.repository;

import com.microservice.lab.web.model.ClassBootcamp;
import com.microservice.lab.web.model.Presensi;
import com.microservice.lab.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PresensiRepository extends JpaRepository<Presensi, String> {
    List<Presensi> findAllByClassBootcampId(ClassBootcamp classBootcamp);
    List<Presensi> findAllByUserId(User user);
    List<Presensi> findAllByIsLate(boolean isLate);
    @Query(value = "SELECT * FROM presensi WHERE CAST(date_submit AS DATE) = ?1", nativeQuery = true)
    Optional<Presensi> findByDateSubmit(String dateSubmit);
}
