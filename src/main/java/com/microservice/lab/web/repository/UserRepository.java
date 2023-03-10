package com.microservice.lab.web.repository;

import com.microservice.lab.web.model.School;
import com.microservice.lab.web.model.Role;
import com.microservice.lab.web.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User getUserById(String id);
    Page<User> findAllBySchoolIdAndRoleId(School schoolId, Role roleId, Pageable pageable);

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);
}
