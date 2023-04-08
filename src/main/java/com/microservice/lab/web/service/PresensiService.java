package com.microservice.lab.web.service;

import com.microservice.lab.web.dto.PresensiDTO;
import com.microservice.lab.web.model.Presensi;
import com.microservice.lab.web.model.School;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.Optional;

public interface PresensiService {
    Presensi absen(PresensiDTO presensiDTO);
    Presensi permit(PresensiDTO presensiDTO, Integer id);
    Page<Presensi> findAllData(String keyword, String isLate, School school, Pageable pageable);
    Map<String, Boolean> absentAvailable();
    Page<Presensi> totalPresent(Pageable pageable);

    Page<Presensi> totalPermission(Pageable pageable);
    Long totalLate();
    
    Optional<Presensi> lastAbsent();
}
