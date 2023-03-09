package com.microservice.lab.web.service;

import com.microservice.lab.web.dto.PresensiDTO;
import com.microservice.lab.web.model.Presensi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface PresensiService {
    Presensi absen(PresensiDTO presensiDTO);
    Page<Presensi> findAllData(Boolean isLate, Pageable pageable);
    Map<String, Boolean> absentAvailable();
}
