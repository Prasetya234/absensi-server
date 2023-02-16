package com.microservice.lab.web.controller;

import com.microservice.lab.configuration.response.CommonResponse;
import com.microservice.lab.configuration.response.ResponseHelper;
import com.microservice.lab.web.model.Reason;
import com.microservice.lab.web.service.ReasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reason")
public class ReasonController {

    private ReasonService reasonService;

    @Autowired
    public ReasonController(ReasonService reasonServicee) {
        this.reasonService = reasonServicee;
    }

    @GetMapping
    public CommonResponse<List<Reason>> findAll() {
        return ResponseHelper.ok(reasonService.findAll());
    }

    @PostMapping
    public CommonResponse<Reason> add(@RequestBody Reason reason) {
        return ResponseHelper.ok(reasonService.add(reason));
    }

    @PutMapping("/{id}")
    public CommonResponse<Reason> update(@PathVariable("id") Integer id, @RequestBody Reason reason) {
        return ResponseHelper.ok(reasonService.update(id, reason));
    }

    @DeleteMapping("/{id}")
    public CommonResponse<Object> delete(@PathVariable("id") Integer id) {
        return ResponseHelper.ok(reasonService.delete(id));
    }
}
