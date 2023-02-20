package com.microservice.lab.web.serviceImpl;

import com.microservice.lab.configuration.exception.NotFoundException;
import com.microservice.lab.web.model.GoingHomeEarly;
import com.microservice.lab.web.repository.GoingHomeEarlyRepository;
import com.microservice.lab.web.service.GoingHomeEarlyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoingHomeEarlyServiceImpl implements GoingHomeEarlyService {

    private GoingHomeEarlyRepository goingHomeEarlyRepository;

    @Autowired
    public GoingHomeEarlyServiceImpl(GoingHomeEarlyRepository goingHomeEarlyRepository) {
        this.goingHomeEarlyRepository = goingHomeEarlyRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<GoingHomeEarly> findAll() {
        return goingHomeEarlyRepository.findAll();
    }

    @Transactional
    @Override
    public GoingHomeEarly add(GoingHomeEarly goingHomeEarly) {
        return goingHomeEarlyRepository.save(goingHomeEarly);
    }

    @Transactional
    @Override
    public GoingHomeEarly update(Integer id, GoingHomeEarly goingHomeEarly) {
        GoingHomeEarly goingHomeEarly1 = goingHomeEarlyRepository.findById(id).orElseThrow(() -> new NotFoundException("Going Home Early Id NOT FOUND"));
        goingHomeEarly1.setNote(goingHomeEarly.getNote());
        goingHomeEarly1.setDate(goingHomeEarly.getDate());
        goingHomeEarly1.setMarkBy(goingHomeEarly.getMarkBy());
        return goingHomeEarlyRepository.save(goingHomeEarly1);
    }

    @Transactional
    @Override
    public Map<String, Object> delete(Integer id) {
        try {
            goingHomeEarlyRepository.deleteById(id);
            Map<String, Object> obj = new HashMap<>();
            obj.put("deleted", Boolean.TRUE);
            return obj;
        } catch (Exception e) {
            throw new NotFoundException("Going Home Early Id NOT FOUND");
        }
    }
}
