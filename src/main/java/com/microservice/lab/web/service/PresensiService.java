package com.microservice.lab.web.service;

import com.microservice.lab.web.dto.PresensiDTO;
import com.microservice.lab.web.model.Presensi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.Optional;

public interface PresensiService {
    Presensi absen(PresensiDTO presensiDTO);
    Page<Presensi> findAllData(String keyword, Boolean isLate, Pageable pageable);
    Map<String, Boolean> absentAvailable();
    Long totalPresent();
    Long totalLate();

    Optional<Presensi> lastAbsent();
}
