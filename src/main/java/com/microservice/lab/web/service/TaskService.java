package com.microservice.lab.web.service;

import com.microservice.lab.web.dto.TaskDTO;
import com.microservice.lab.web.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;


public interface TaskService {

    Page<Task> tasks(String type, String userId, Pageable pageable);
    Task addTask(TaskDTO tasksDTO);

    Page<Task> tasksByUserId(Pageable pageable, String id);

    Task updateTask(Integer id, TaskDTO taskDTO);

    Task updateStatusTask(Integer id, Task task);

    Task findTaskById(Integer id);

    Map<String, Object> deleteTask(Integer id);

}
