package com.microservice.lab.web.serviceImpl;

import com.microservice.lab.configuration.exception.NotFoundException;
import com.microservice.lab.web.model.Reason;
import com.microservice.lab.web.repository.ReasonRepository;
import com.microservice.lab.web.service.ReasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReasonServiceImpl implements ReasonService {

    private ReasonRepository reasonRepository;

    @Autowired
    public ReasonServiceImpl(ReasonRepository reasonRepositoryy) {
        this.reasonRepository = reasonRepositoryy;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Reason> findAll() {
        return reasonRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Reason findById(Integer id) {
        return reasonRepository.findById(id).orElseThrow(() -> new NotFoundException("Reason ID Not Found"));
    }


    @Transactional
    @Override
    public Reason add(Reason reason) {
       return reasonRepository.save(reason);
    }

    @Transactional
    @Override
    public Reason update(Integer id, Reason reason) {
        Reason reasonn = reasonRepository.findById(id).orElseThrow(() -> new NotFoundException("Reason id NOT FOUND"));
        reasonn.setName(reason.getName());
        return reasonRepository.save(reasonn);
    }

    @Transactional
    @Override
    public Map<String, Object> delete(Integer id) {
        try {
            reasonRepository.deleteById(id);
            Map<String, Object> obj = new HashMap<>();
            obj.put("deleted", Boolean.TRUE);
            return obj;
        } catch (Exception e) {
            throw new NotFoundException("Reason Id NOT FOUND");
        }
    }
}
