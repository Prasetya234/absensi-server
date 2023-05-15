package com.microservice.lab.web.controller;

import com.microservice.lab.configuration.response.CommonResponse;
import com.microservice.lab.configuration.response.ResponseHelper;
import com.microservice.lab.web.dto.TaskDTO;
import com.microservice.lab.web.model.Task;
import com.microservice.lab.web.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PreAuthorize("hasAnyAuthority('INSTRUCTOR', 'ADMIN')")
    @PostMapping("/new")
    public CommonResponse<Task> newTask(@RequestBody TaskDTO taskDTO) {
        return ResponseHelper.ok(taskService.addTask(taskDTO));
    }

    @GetMapping
    public CommonResponse<Page<Task>> tasks(@RequestParam(name = "type", required = false) String type, @RequestParam(name = "userId", required = false) String userId, @RequestParam(name = "page", defaultValue = "0", required = false) int page, @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseHelper.ok(taskService.tasks(type, userId, pageable));
    }

    @GetMapping("/user")
    public CommonResponse<Page<Task>> tasksByUserId(@RequestParam(name = "userId", required = false) String userId, @RequestParam(name = "page", defaultValue = "0", required = false) int page, @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseHelper.ok(taskService.tasksByUserId(pageable, userId));
    }

    @PreAuthorize("hasAnyAuthority('INSTRUCTOR', 'ADMIN')")
    @GetMapping("/{id}")
    public CommonResponse<Task> findTaskById(@PathVariable("id") Integer id) {
        return ResponseHelper.ok(taskService.findTaskById(id));
    }

    @PreAuthorize("hasAnyAuthority('INSTRUCTOR', 'ADMIN')")
    @PutMapping("/{id}")
    public CommonResponse<Task> updateTask(@PathVariable("id") Integer id, @RequestBody TaskDTO taskDTO) {
        return ResponseHelper.ok(taskService.updateTask(id, taskDTO));
    }

    @PreAuthorize("hasAnyAuthority('INSTRUCTOR', 'ADMIN')")
    @PutMapping("/status/{id}")
    public CommonResponse<Task> updateStatusTask(@PathVariable("id") Integer id, @RequestBody Task task) {
        return ResponseHelper.ok(taskService.updateStatusTask(id, task));
    }

    @PreAuthorize("hasAnyAuthority('INSTRUCTOR', 'ADMIN')")
    @DeleteMapping("/{id}")
    public CommonResponse<Object> deleteTask(@PathVariable("id") Integer id) {
        return ResponseHelper.ok(taskService.deleteTask(id));
    }
}
