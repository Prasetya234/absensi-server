package com.microservice.lab.web.serviceImpl;

import com.microservice.lab.configuration.data.IAuthenticationFacade;
import com.microservice.lab.configuration.exception.BussinesException;
import com.microservice.lab.web.dto.PresensiDTO;
import com.microservice.lab.web.model.*;
import com.microservice.lab.web.repository.FaceUserRepository;
import com.microservice.lab.web.repository.PresensiRepository;
import com.microservice.lab.web.repository.UserRepository;
import com.microservice.lab.web.service.PresensiService;
import com.microservice.lab.web.service.ReasonService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service
public class PresensiServiceImpl implements PresensiService {

    private IAuthenticationFacade authenticationFacade;
    private PresensiRepository presensiRepository;
    private FaceUserRepository faceUserRepository;
    private ModelMapper modelMapper;

    private UserRepository userRepository;

    private ReasonService reasonService;


    @Autowired
    public PresensiServiceImpl(IAuthenticationFacade authenticationFacade, PresensiRepository presensiRepository, ModelMapper modelMapper, FaceUserRepository faceUserRepository, UserRepository userRepository, ReasonService reasonService) {
        this.authenticationFacade = authenticationFacade;
        this.presensiRepository = presensiRepository;
        this.faceUserRepository = faceUserRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.reasonService = reasonService;
    }

    @Override
    public Presensi absen(PresensiDTO presensiDTO) {
        if (presensiRepository.findByDateSubmit(dateNow(), authenticationFacade.getAuthentication().getId()).isPresent()) {
            throw new BussinesException("YOU ALREADY ABSENCE");
        }
        boolean isFaceReady = false;
        FaceUser faceUser = faceUserRepository.findByUserId(authenticationFacade.getAuthentication()).get();
        Presensi presensi = modelMapper.map(presensiDTO, Presensi.class);
        for (String faceNumb : faceUser.getDetectorScore().split(",")) {
            if (faceNumb.equals(presensiDTO.getFaceNumber())) {
                isFaceReady = true;
            }
        }
        if (!isFaceReady) {
            throw new BussinesException("FACE DATA NOT FOUND");
        }
        presensi.setSchoolId(authenticationFacade.getAuthentication().getSchoolId());
        presensi.setUserId(authenticationFacade.getAuthentication());
        return presensiRepository.save(presensi);
    }

    @Override
    public Presensi permit(PresensiDTO presensiDTO, Integer id) {
        if (presensiRepository.findByDateSubmit(dateNow(), authenticationFacade.getAuthentication().getId()).isPresent()) {
            throw new BussinesException("YOU ALREADY ABSENCE");
        }
        Presensi permit = modelMapper.map(presensiDTO, Presensi.class);
        permit.setDateSubmit(new Date());
        permit.setNote(presensiDTO.getNote());
        permit.setFaceNumber("");
        permit.setLongitude(null);
        permit.setLatitude(null);
        permit.setIsLate(false);
        permit.setPermissionAttend(true);
        permit.setReasonId(reasonService.add(new Reason(id, presensiDTO.getReason())));
        permit.setSchoolId(authenticationFacade.getAuthentication().getSchoolId());
        permit.setUserId(authenticationFacade.getAuthentication());
        return presensiRepository.save(permit);
    }

    @Transactional(readOnly = true)
    public Optional<Presensi> lastAbsent() {
        return presensiRepository.findByDateSubmit(dateNow(), authenticationFacade.getAuthentication().getId());
    }

    @Transactional(readOnly = true)
    @Override
    public List<Presensi> findAllData(String keyword, String isLate, String school) {
        return presensiRepository.findAllByIsLate(keyword, isLate, school);
    }

    @Override
    public Map<String, Boolean> absentAvailable() {
        Map<String, Boolean> obj = new HashMap<>();
        obj.put("absen", presensiRepository.findByDateSubmit(dateNow(), authenticationFacade.getAuthentication().getId()).isPresent());
        obj.put("face", faceUserRepository.findByUserId(authenticationFacade.getAuthentication()).isPresent());
        return obj;
    }

    @Override
    public Page<Presensi> totalPresent(Pageable pageable) {
        return presensiRepository.countPresentByUserId(authenticationFacade.getAuthentication(), pageable);
    }

    @Override
    public Page<Presensi> totalPermission(Pageable pageable) {
        return presensiRepository.countPermissionByUserId(authenticationFacade.getAuthentication(), pageable);
    }

    @Override
    public Long totalLate() {
        return presensiRepository.countByUserIdAndIsLate(authenticationFacade.getAuthentication(), true);
    }

    private String dateNow() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(df.format(new Date()));
        return df.format(new Date());
    }

}
