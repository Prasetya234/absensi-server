package com.microservice.lab.web.controller;

import com.microservice.lab.configuration.response.CommonResponse;
import com.microservice.lab.configuration.response.ResponseHelper;
import com.microservice.lab.web.dto.PresensiDTO;
import com.microservice.lab.web.model.Presensi;
import com.microservice.lab.web.service.PresensiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public CommonResponse<Page<Presensi>> getAllDataAbsen(@RequestParam(name = "isLate", required = false) Boolean isLate, @RequestParam(name = "page", required = false, defaultValue = "0") int page, @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseHelper.ok(presensiService.findAllData(isLate, pageable));
    }

    @GetMapping("/check")
    public CommonResponse<Map<String, Boolean>> checkAlreadyAbsen() {
        return ResponseHelper.ok(presensiService.absentAvailable());
    }
}
