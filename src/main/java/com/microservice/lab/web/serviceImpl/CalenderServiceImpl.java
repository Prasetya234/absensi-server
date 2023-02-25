package com.microservice.lab.web.serviceImpl;

import com.microservice.lab.configuration.data.IAuthenticationFacade;
import com.microservice.lab.utils.DateConfig;
import com.microservice.lab.web.dto.CalenderRequest;
import com.microservice.lab.web.dto.DateResponse;
import com.microservice.lab.web.model.Calender;
import com.microservice.lab.web.model.User;
import com.microservice.lab.web.repository.CalenderRepository;
import com.microservice.lab.web.repository.SchoolRepository;
import com.microservice.lab.web.service.CalenderService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class CalenderServiceImpl implements CalenderService {

    private IAuthenticationFacade authenticationFacade;
    private SchoolRepository schoolRepository;
    private CalenderRepository calenderRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CalenderServiceImpl(IAuthenticationFacade authenticationFacade, SchoolRepository schoolRepository, CalenderRepository calenderRepository, ModelMapper modelMapper) {
        this.authenticationFacade = authenticationFacade;
        this.schoolRepository = schoolRepository;
        this.calenderRepository = calenderRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public Calender add(CalenderRequest calenderRequest) {
        Calender calender = modelMapper.map(calenderRequest, Calender.class);
        User user = authenticationFacade.getAuthentication();
        calender.setMaker(user.getFirstName() + " " + user.getLastName());
        calender.setSchoolId(user.getSchoolId());
        return calenderRepository.save(calender);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Calender> findAll(Integer month, Integer year) {
        User user = authenticationFacade.getAuthentication();
        if (month == null || year == null) {
            DateResponse date = new DateConfig().getDateRange();
            return calenderRepository.findAllByDateAfterAndDateBeforeAndSchoolId(date.getStart(), date.getEnd(), user.getSchoolId());
        }
        DateResponse date = new DateConfig().getDateRange(month, year);
        return calenderRepository.findAllByDateAfterAndDateBeforeAndSchoolId(date.getStart(), date.getEnd(), user.getSchoolId());
    }

}
