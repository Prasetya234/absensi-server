package com.microservice.lab.web.service;

import com.microservice.lab.web.dto.PresensiDTO;
import com.microservice.lab.web.model.Presensi;

import java.util.List;
import java.util.Map;

public interface PresensiService {
    Presensi absen(PresensiDTO presensiDTO);
    List<Presensi> findAllData(Boolean isLate);
    Map<String, Boolean> absentAvailable();
}
