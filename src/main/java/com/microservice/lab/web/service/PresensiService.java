package com.microservice.lab.web.service;

import com.microservice.lab.web.dto.PresensiDTO;
import com.microservice.lab.web.model.Presensi;
import com.microservice.lab.web.model.Reason;
import com.microservice.lab.web.model.School;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PresensiService {
    Presensi absen(PresensiDTO presensiDTO);

    Presensi absenClick(PresensiDTO presensiDTO);
    Presensi permit(PresensiDTO presensiDTO, Integer id);
    List<Presensi> findAllData(String keyword, String isLate, String school);
    Map<String, Boolean> absentAvailable();

    Boolean findByName(String name);
    Page<Presensi> totalPresent(Pageable pageable);

    Page<Presensi> totalPermission(Pageable pageable);
    Long totalLate();
    
    Optional<Presensi> lastAbsent();
}
