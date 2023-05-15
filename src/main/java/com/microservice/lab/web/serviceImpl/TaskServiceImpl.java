package com.microservice.lab.web.serviceImpl;

import com.microservice.lab.configuration.data.IAuthenticationFacade;
import com.microservice.lab.configuration.exception.NotFoundException;
import com.microservice.lab.web.dto.TaskDTO;
import com.microservice.lab.web.model.Task;
import com.microservice.lab.web.repository.TaskRepository;
import com.microservice.lab.web.repository.UserRepository;
import com.microservice.lab.web.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TaskServiceImpl implements TaskService {

    private IAuthenticationFacade authenticationFacade;
    private TaskRepository taskRepo;
    private ModelMapper modelMapper;
    private UserRepository userRepo;

    @Autowired
    public TaskServiceImpl(IAuthenticationFacade authenticationFacade, TaskRepository taskRepo, ModelMapper modelMapper, UserRepository userRepo) {
        this.authenticationFacade = authenticationFacade;
        this.taskRepo = taskRepo;
        this.modelMapper = modelMapper;
        this.userRepo = userRepo;
    }

    @Override
    public Task addTask(TaskDTO taskDTO) {
        Task newTask = modelMapper.map(taskDTO, Task.class);
        newTask.setNote(taskDTO.getNote());
        newTask.setAssignmentDate(new Date());
        newTask.setDueDate(taskDTO.getDueDate());
        newTask.setIsClosed(false);
        newTask.setTaskType(taskDTO.getTaskType());
        if (taskDTO.getTaskType().equals("JOINTLY") || taskDTO.getUser() == null) {
            newTask.setUserId(authenticationFacade.getAuthentication());
        } else {
            newTask.setUserId(userRepo.findById(taskDTO.getUser()).orElseThrow(() -> new NotFoundException("User ID Not Found")));
        }
        return taskRepo.save(newTask);
    }

    @Override
    public Page<Task> tasks(String type, String userId, Pageable pageable) {
        return taskRepo.filterTaskByType(type, userId, pageable);
    }

    @Override
    public Page<Task> tasksByUserId(Pageable pageable, String id) {
        String userId = userRepo.getUserById(id).getId();
        return taskRepo.tasksByUserId(pageable, userId);
    }

    @Override
    public Task findTaskById(Integer id) {
        return taskRepo.findById(id).orElseThrow(() -> new NotFoundException("Task ID Not Found"));
    }

    @Override
    public Task updateTask(Integer id, TaskDTO taskDTO) {
        Task update = taskRepo.findById(id).orElseThrow(() -> new NotFoundException("Task ID Not Found"));
        update.setNote(taskDTO.getNote());
        update.setAssignmentDate(taskDTO.getAssignmentDate());
        update.setUpdateAt(new Date());
        update.setDueDate(taskDTO.getDueDate());
        update.setIsClosed(taskDTO.getIsClosed());
        update.setTaskType(taskDTO.getTaskType());
        if (taskDTO.getTaskType().equals("JOINTLY") || taskDTO.getUser() == null) {
            update.setUserId(authenticationFacade.getAuthentication());
        } else {
            update.setUserId(userRepo.findById(taskDTO.getUser()).orElseThrow(() -> new NotFoundException("User ID Not Found")));
        }
        return taskRepo.save(update);
    }

    @Override
    public Task updateStatusTask(Integer id, Task task) {
        Task newStatus = taskRepo.findById(id).orElseThrow(() -> new NotFoundException("Task ID Not Found"));
        newStatus.setIsClosed(task.getIsClosed());
        return taskRepo.save(newStatus);
    }

    @Override
    public Map<String, Object> deleteTask(Integer id) {
        try {
            taskRepo.deleteById(id);
            Map<String, Object> obj = new HashMap<>();
            obj.put("deleted", Boolean.TRUE);
            return obj;
        } catch (Exception e) {
            throw new NotFoundException("Task ID Not Found");
        }
    }
}
