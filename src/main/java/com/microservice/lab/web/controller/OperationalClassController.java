package com.microservice.lab.web.controller;

import com.microservice.lab.configuration.response.CommonResponse;
import com.microservice.lab.configuration.response.ResponseHelper;
import com.microservice.lab.web.model.OperationalClass;
import com.microservice.lab.web.service.OperationalClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/operational-class")
public class OperationalClassController {
    private OperationalClassService operationalClassService;
    @Autowired
    public OperationalClassController(OperationalClassService operationalClassService) {
        this.operationalClassService = operationalClassService;
    }

    @GetMapping
    public CommonResponse<OperationalClass> findOperationalClass() {
        return ResponseHelper.ok(operationalClassService.getOperationalClass());
    }
}
