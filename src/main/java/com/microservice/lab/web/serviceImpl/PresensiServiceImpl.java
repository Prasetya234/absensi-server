package com.microservice.lab.web.serviceImpl;
import com.microservice.lab.configuration.data.IAuthenticationFacade;
import com.microservice.lab.configuration.exception.BussinesException;
import com.microservice.lab.web.dto.PresensiDTO;
import com.microservice.lab.web.model.FaceUser;
import com.microservice.lab.web.model.Presensi;
import com.microservice.lab.web.repository.FaceUserRepository;
import com.microservice.lab.web.repository.PresensiRepository;
import com.microservice.lab.web.repository.UserRepository;
import com.microservice.lab.web.service.PresensiService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class PresensiServiceImpl implements PresensiService {

    private IAuthenticationFacade authenticationFacade;
    private PresensiRepository presensiRepository;
    private FaceUserRepository faceUserRepository;
    private ModelMapper modelMapper;

    private UserRepository userRepository;

    @Autowired
    public PresensiServiceImpl(IAuthenticationFacade authenticationFacade, PresensiRepository presensiRepository,ModelMapper modelMapper, FaceUserRepository faceUserRepository, UserRepository userRepository) {
        this.authenticationFacade = authenticationFacade;
        this.presensiRepository = presensiRepository;
        this.faceUserRepository = faceUserRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public Presensi absen(PresensiDTO presensiDTO) {
        if (presensiRepository.findByDateSubmit(dateNow(), authenticationFacade.getAuthentication().getId()).isPresent()) throw new BussinesException("YOU ALREADY ABSENCE");
        boolean isFaceReady = false;
        FaceUser faceUser = faceUserRepository.findByUserId(authenticationFacade.getAuthentication()).get();
        Presensi presensi = modelMapper.map(presensiDTO, Presensi.class);
        for (String faceNumb: faceUser.getDetectorScore().split(","))  {
            if (faceNumb.equals(presensiDTO.getFaceNumber())) isFaceReady = true;
        }
        if (!isFaceReady) throw new BussinesException("FACE DATA NOT FOUND");
        presensi.setSchoolId(authenticationFacade.getAuthentication().getSchoolId());
        presensi.setUserId(authenticationFacade.getAuthentication());
        return presensiRepository.save(presensi);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Presensi> findAllData(Boolean isLate, Pageable pageable) {
        if (isLate == null) return presensiRepository.findAllBySchoolId(authenticationFacade.getAuthentication().getSchoolId(), pageable);
         return presensiRepository.findAllByIsLate(isLate, pageable);
    }

    @Override
    public Map<String, Boolean> absentAvailable() {
        Map<String, Boolean> obj = new HashMap<>();
        obj.put("absen", presensiRepository.findByDateSubmit(dateNow(), authenticationFacade.getAuthentication().getId()).isPresent());
        obj.put("face", faceUserRepository.findByUserId(authenticationFacade.getAuthentication()).isPresent());
        return  obj;
    }

    private String dateNow() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(df.format(new Date()));
        return df.format(new Date());
    }
}
