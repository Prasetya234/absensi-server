package com.microservice.lab.web.controller;

import com.microservice.lab.configuration.data.IAuthenticationFacade;
import com.microservice.lab.configuration.response.CommonResponse;
import com.microservice.lab.configuration.response.ResponseHelper;
import com.microservice.lab.web.dto.PresensiDTO;
import com.microservice.lab.web.model.Presensi;
import com.microservice.lab.web.repository.ReasonRepository;
import com.microservice.lab.web.service.PresensiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/presensi")
public class PresensiController {

    private PresensiService presensiService;

    private IAuthenticationFacade authenticationFacade;

    private ReasonRepository reasonRepository;

    @Autowired
    public PresensiController(PresensiService presensiService, IAuthenticationFacade authenticationFacade, ReasonRepository reasonRepository) {
        this.presensiService = presensiService;
        this.authenticationFacade = authenticationFacade;
        this.reasonRepository = reasonRepository;
    }

    @PostMapping("/absen-now")
    public CommonResponse<Presensi> absenNow(@RequestBody PresensiDTO presensiDTO) {
        return ResponseHelper.ok(presensiService.absen(presensiDTO));
    }

    @PostMapping("/absen")
    public CommonResponse<Presensi> absen(@RequestBody PresensiDTO presensiDTO) {
        return ResponseHelper.ok(presensiService.absenClick(presensiDTO));
    }

    @PostMapping("/permit")
    public CommonResponse<Presensi> permit(@RequestBody PresensiDTO presensiDTO, Integer id) {
        return ResponseHelper.ok(presensiService.permit(presensiDTO, id));
    }

    @PreAuthorize("hasAnyAuthority('INSTRUCTOR', 'ADMIN')")
    @GetMapping
    public CommonResponse<List<Presensi>> getAllDataAbsen(@RequestParam(name = "search", required = false) String keyword, @RequestParam(name = "isLate", required = false) String isLate) {
        String school = authenticationFacade.getAuthentication().getSchoolId().getId();
        return ResponseHelper.ok(presensiService.findAllData(keyword, isLate, school));
    }

    @GetMapping("/check")
    public CommonResponse<Map<String, Boolean>> checkAlreadyAbsen() {
        return ResponseHelper.ok(presensiService.absentAvailable());
    }

    @GetMapping("/last")
    public CommonResponse<Optional<Presensi>> lastAbsent() {
        return ResponseHelper.ok(presensiService.lastAbsent());
    }

    @GetMapping("/total-present")
    public CommonResponse<Page<Presensi>> presentTotal(@RequestParam(name = "page", required = false, defaultValue = "0") int page, @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseHelper.ok(presensiService.totalPresent(pageable));
    }

    @GetMapping("/total-permission")
    public CommonResponse<Page<Presensi>> permitTotal(@RequestParam(name = "page", required = false, defaultValue = "0") int page, @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseHelper.ok(presensiService.totalPermission(pageable));
    }

    @GetMapping("/total-late")
    public CommonResponse<Long> lateTotal() {
        return ResponseHelper.ok(presensiService.totalLate());
    }
}
