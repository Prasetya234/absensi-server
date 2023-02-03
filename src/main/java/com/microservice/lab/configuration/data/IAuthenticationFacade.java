package com.microservice.lab.configuration.data;

import com.microservice.lab.web.model.User;

public interface IAuthenticationFacade {
    User getAuthentication();
}
