package com.microservice.lab.web.controller;

import com.microservice.lab.configuration.response.CommonResponse;
import com.microservice.lab.configuration.response.ResponseHelper;
import com.microservice.lab.web.dto.PresensiDTO;
import com.microservice.lab.web.model.Presensi;
import com.microservice.lab.web.service.PresensiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/presensi")
public class PresensiController {

    private PresensiService presensiService;

    @Autowired
    public PresensiController(PresensiService presensiService) {
        this.presensiService = presensiService;
    }

    @PostMapping("/absen-now")
    public CommonResponse<Presensi> absenNow(@RequestBody PresensiDTO presensiDTO) {
        return ResponseHelper.ok(presensiService.absen(presensiDTO));
    }

    @PreAuthorize("hasAnyAuthority('INSTRUCTOR', 'ADMIN')")
    @GetMapping
    public CommonResponse<List<Presensi>> getAllDataAbsen(@RequestParam(name = "isLate", required = false) Boolean isLate) {
        return ResponseHelper.ok(presensiService.findAllData(isLate));
    }

    @GetMapping("/check")
    public CommonResponse<Map<String, Boolean>> checkAlreadyAbsen() {
        return ResponseHelper.ok(presensiService.absentAvailable());
    }
}
