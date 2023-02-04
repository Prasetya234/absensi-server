package com.microservice.lab.web.repository;

import com.microservice.lab.web.model.Calender;
import com.microservice.lab.web.model.ClassBootcamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CalenderRepository  extends JpaRepository<Calender, Integer>{
    List<Calender> findAllByDateAfterAndDateBeforeAndClassBootcampId(Date after, Date before, ClassBootcamp classBootcampId);
    List<Calender> findAllByClassBootcampId(ClassBootcamp classBootcampId);
}
