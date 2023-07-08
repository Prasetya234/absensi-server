package com.microservice.lab.web.serviceImpl;

import com.microservice.lab.configuration.data.IAuthenticationFacade;
import com.microservice.lab.configuration.exception.NotFoundException;
import com.microservice.lab.web.dto.ToDoListDTO;
import com.microservice.lab.web.model.ToDoList;
import com.microservice.lab.web.repository.ToDoListRepository;
import com.microservice.lab.web.service.ToDoListService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ToDoListServiceImpl implements ToDoListService {

    private ToDoListRepository todoRepo;
    private ModelMapper modelMapper;
    private IAuthenticationFacade authenticationFacade;

    @Autowired
    public ToDoListServiceImpl(ToDoListRepository todoRepo, ModelMapper modelMapper, IAuthenticationFacade authenticationFacade) {
        this.todoRepo = todoRepo;
        this.modelMapper = modelMapper;
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    public ToDoList newTodo(ToDoListDTO todoDTO) {
        ToDoList todo = modelMapper.map(todoDTO, ToDoList.class);
        todo.setNote(todoDTO.getNote());
        todo.setStatus(false);
        todo.setUserId(authenticationFacade.getAuthentication());
        return todoRepo.save(todo);
    }

    @Override
    public Boolean todoAvailable(String userId) {
        if (todoRepo.findByCreateAt(dateNow(), userId).isEmpty()) {
            return false;
        } return true;
    }

    @Override
    public Page<ToDoList> todolistByUserId(String userId, Pageable pageable) {
        return todoRepo.getTodoByUserId(dateNow(), userId, pageable);
    }

    // (perlu perbaikan) karena semua role bisa mengubah todolist sesuai id yang diinginkan ketika trial di postman tapi kalau di FE nya insyaAllah aman
    @Override
    public ToDoList updateNoteTodo(Integer todoId, ToDoList toDoList) {
        ToDoList todo = todoRepo.findById(todoId).orElseThrow(() -> new NotFoundException("Todo ID Not Found"));
        todo.setNote(toDoList.getNote());
        return todoRepo.save(todo);
    }

    @Override
    public ToDoList updateStatusTodo(Integer id, ToDoList toDoList) {
        ToDoList todo = todoRepo.findById(id).orElseThrow(() -> new NotFoundException("Todo ID Not Found"));
        todo.setStatus(toDoList.getStatus());
        return todoRepo.save(todo);
    }

    @Override
    public Map<String, Object> deleteTodo(Integer id) {
        try {
            todoRepo.deleteById(id);
            Map<String, Object> obm = new HashMap<>();
            obm.put("deleted", Boolean.TRUE);
            return obm;
        } catch (Exception e) {
            throw new NotFoundException("Todo ID Not Found");
        }
    }

    private String dateNow() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(new Date());
    }
}
