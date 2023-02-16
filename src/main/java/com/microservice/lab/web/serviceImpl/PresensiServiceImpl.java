package com.microservice.lab.web.serviceImpl;
import com.microservice.lab.configuration.data.IAuthenticationFacade;
import com.microservice.lab.configuration.exception.BussinesException;
import com.microservice.lab.web.dto.PresensiDTO;
import com.microservice.lab.web.model.FaceUser;
import com.microservice.lab.web.model.Presensi;
import com.microservice.lab.web.repository.FaceUserRepository;
import com.microservice.lab.web.repository.PresensiRepository;
import com.microservice.lab.web.service.PresensiService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
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

    @Autowired
    public PresensiServiceImpl(IAuthenticationFacade authenticationFacade, PresensiRepository presensiRepository,ModelMapper modelMapper, FaceUserRepository faceUserRepository) {
        this.authenticationFacade = authenticationFacade;
        this.presensiRepository = presensiRepository;
        this.faceUserRepository = faceUserRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Presensi absen(PresensiDTO presensiDTO) {
        if (presensiRepository.findByDateSubmit(dateNow()).isPresent()) throw new BussinesException("YOU ALREADY ABSENCE");
        boolean isFaceReady = false;
        FaceUser faceUser = faceUserRepository.findByUserId(authenticationFacade.getAuthentication()).get();
        Presensi presensi = modelMapper.map(presensiDTO, Presensi.class);
        for (String faceNumb: faceUser.getDetectorScore().split(","))  {
            if (faceNumb.equals(presensiDTO.getFaceNumber())) isFaceReady = true;
        }
        if (!isFaceReady) throw new BussinesException("FACE DATA NOT FOUND");
        presensi.setClassBootcampId(authenticationFacade.getAuthentication().getClassBootcampId());
        presensi.setUserId(authenticationFacade.getAuthentication());
        return presensiRepository.save(presensi);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Presensi> findAllData(Boolean isLate) {
        if (isLate == null) return presensiRepository.findAllByClassBootcampId(authenticationFacade.getAuthentication().getClassBootcampId());
         return presensiRepository.findAllByIsLate(isLate);
    }

    @Override
    public Map<String, Boolean> absentAvailable() {
        Map<String, Boolean> obj = new HashMap<>();
        obj.put("absen", presensiRepository.findByDateSubmit(dateNow()).isEmpty());
        obj.put("face", faceUserRepository.findByUserId(authenticationFacade.getAuthentication()).isPresent());
        return  obj;
    }

    private String dateNow() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(df.format(new Date()));
        return df.format(new Date());
    }
}
