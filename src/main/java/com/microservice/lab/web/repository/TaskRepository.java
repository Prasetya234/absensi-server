package com.microservice.lab.web.repository;

import com.microservice.lab.web.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    @Query(value = "SELECT * FROM tasks WHERE user_id = ?1", nativeQuery = true)
    Page<Task> tasksByUserId(Pageable pageable, String userId);

    @Query(value = "SELECT * FROM tasks WHERE task_type LIKE CONCAT('%', ?1, '%') AND user_id LIKE CONCAT('%', ?2, '%') ORDER BY update_at DESC", nativeQuery = true)
    Page<Task> filterTaskByType(String type, String userId, Pageable pageable);
}
    