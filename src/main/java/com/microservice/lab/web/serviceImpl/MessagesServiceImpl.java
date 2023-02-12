package com.microservice.lab.web.serviceImpl;

import com.microservice.lab.configuration.data.AuthenticationFacade;
import com.microservice.lab.web.model.Messages;
import com.microservice.lab.web.repository.MassagesRepository;
import com.microservice.lab.web.service.MessagesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class MessagesServiceImpl implements MessagesService {

    private MassagesRepository massagesRepository;
    private AuthenticationFacade authenticationFacade;

    @Autowired
    public MessagesServiceImpl(MassagesRepository massagesRepository, AuthenticationFacade authenticationFacade) {
        this.massagesRepository = massagesRepository;
        this.authenticationFacade = authenticationFacade;
    }

    @Transactional
    @Override
    public String send(Messages messages) {
        massagesRepository.save(messages);
        return "Success Send Message";
    }

    @Transactional(readOnly = true)
    @Override
    public List<Messages> messages() {
        return massagesRepository.findAllBySchoolId(authenticationFacade.getAuthentication().getClassBootcampId().getId());
    }
}
