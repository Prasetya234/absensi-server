package com.microservice.lab.web.service;

import com.microservice.lab.web.dto.ToDoListDTO;
import com.microservice.lab.web.model.ToDoList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ToDoListService {

    ToDoList newTodo(ToDoListDTO todoDTO);

    Page<ToDoList> todolistByUserId(String userId, Pageable pageable);

    ToDoList updateNoteTodo(Integer todoId, ToDoList toDoList);
    ToDoList updateStatusTodo(Integer todoId, ToDoList toDoList);
    Boolean todoAvailable(String userId);

    Map<String, Object> deleteTodo(Integer id);
}
