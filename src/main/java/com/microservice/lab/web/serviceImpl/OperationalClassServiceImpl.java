package com.microservice.lab.web.serviceImpl;

import com.microservice.lab.configuration.data.IAuthenticationFacade;
import com.microservice.lab.web.model.OperationalClass;
import com.microservice.lab.web.service.OperationalClassService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OperationalClassServiceImpl implements OperationalClassService {

    private IAuthenticationFacade authenticationFacade;

    @Autowired
    public OperationalClassServiceImpl(IAuthenticationFacade authenticationFacade) {
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    public OperationalClass getOperationalClass() {
        return authenticationFacade.getAuthentication().getSchoolId().getOperationalClass();
    }
}
