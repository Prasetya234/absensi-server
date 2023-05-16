package com.microservice.lab.web.repository;

import com.microservice.lab.web.model.ToDoList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoListRepository extends JpaRepository<ToDoList, Integer> {
    @Query(value = "SELECT * FROM todo_list WHERE CAST(create_at AS DATE) = ?1 AND user_id = ?2 ORDER BY create_at DESC", nativeQuery = true)
    Page<ToDoList> getTodoByUserId(String createAt, String userId, Pageable pageable);
    
    @Query(value = "SELECT * FROM todo_list WHERE CAST(create_at AS DATE) = ?1 AND user_id = ?2", nativeQuery = true)
    List<ToDoList> findByCreateAt(String createAt, String userId);

}
