package com.microservice.lab.web.repository;

import com.microservice.lab.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User getUserById(String id);

    @Query(value = "SELECT * FROM user u WHERE u.first_name LIKE CONCAT('%', ?1, '%') AND u.class_bootcamp_id = ?2 AND u.role_id = ?3", nativeQuery = true)
    List<User> findAllBySchoolIdAndRoleId(String keyword, String schoolId, Integer role);

    @Query(value = "SELECT * FROM user u WHERE u.role_id = ?1 AND u.class_bootcamp_id = ?2", nativeQuery = true)
    User findInstructorIdBySchool(Integer roleId, String schoolId);

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);
}
