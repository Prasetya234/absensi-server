package com.microservice.lab.web.repository;

import com.microservice.lab.web.model.Calender;
import com.microservice.lab.web.model.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CalenderRepository  extends JpaRepository<Calender, Integer>{
    List<Calender> findAllByDateAfterAndDateBeforeAndSchoolId(Date after, Date before, School schoolId);
    List<Calender> findAllBySchoolId(School schoolId);
}
