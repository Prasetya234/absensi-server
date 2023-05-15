package com.microservice.lab.web.controller;

import com.microservice.lab.configuration.data.IAuthenticationFacade;
import com.microservice.lab.configuration.response.CommonResponse;
import com.microservice.lab.configuration.response.ResponseHelper;
import com.microservice.lab.web.dto.ToDoListDTO;
import com.microservice.lab.web.model.ToDoList;
import com.microservice.lab.web.service.ToDoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo-list")
public class ToDoListController {

    private ToDoListService todoService;
    private IAuthenticationFacade authenticationFacade;

    @Autowired
    public ToDoListController(ToDoListService todoService, IAuthenticationFacade authenticationFacade) {
        this.todoService = todoService;
        this.authenticationFacade = authenticationFacade;
    }


    @GetMapping("/check")
    public CommonResponse<Boolean> checkTodo(@RequestParam(name = "userId", required = false) String userId) {
        return ResponseHelper.ok(todoService.todoAvailable(userId));
    }

    @GetMapping
    public CommonResponse<Page<ToDoList>> todoByUserId(@RequestParam(name = "userId", required = false) String userId, @RequestParam(name = "page", defaultValue = "0", required = false) int page, @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseHelper.ok(todoService.todolistByUserId(userId, pageable));
    }

    @PostMapping("/new")
    public CommonResponse<ToDoList> newTodo(@RequestBody ToDoListDTO todoDTO) {
        return ResponseHelper.ok(todoService.newTodo(todoDTO));
    }

    @PutMapping("/note/{id}")
    public CommonResponse<ToDoList> updateNoteTodo(@PathVariable("id") Integer id, @RequestBody ToDoList toDoList) {
        return ResponseHelper.ok(todoService.updateNoteTodo(id, toDoList));
    }

    @PutMapping("/status/{id}")
    public CommonResponse<ToDoList> updateStatusTodo(@PathVariable("id") Integer id, @RequestBody ToDoList toDoList) {
        return ResponseHelper.ok(todoService.updateStatusTodo(id, toDoList));
    }

    @DeleteMapping("/{id}")
    public CommonResponse<Object> deleteTodo(@PathVariable("id") Integer id) {
        return ResponseHelper.ok(todoService.deleteTodo(id));
    }
}
