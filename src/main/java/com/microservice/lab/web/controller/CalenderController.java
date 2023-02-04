package com.microservice.lab.web.controller;

import com.microservice.lab.configuration.response.CommonResponse;
import com.microservice.lab.configuration.response.ResponseHelper;
import com.microservice.lab.web.dto.CalenderRequest;
import com.microservice.lab.web.model.Calender;
import com.microservice.lab.web.service.CalenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/calender")
public class CalenderController {
    private CalenderService calenderService;

    @Autowired
    public CalenderController(CalenderService calenderService) {
        this.calenderService = calenderService;
    }

    @GetMapping
    public CommonResponse<List<Calender>> findCalender(@RequestParam(name = "month", required = false)Integer month,@RequestParam(name = "year", required = false) Integer year) {
        return ResponseHelper.ok(calenderService.findAll(month, year));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    @PostMapping
    public CommonResponse<Calender> add(@RequestBody CalenderRequest calenderRequest) {
        return ResponseHelper.ok(calenderService.add(calenderRequest));
    }
}
