package com.kinatra.todoapp.controllers;

import com.kinatra.todoapp.models.Task;
import com.kinatra.todoapp.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/tasks")
public class TaskController {
    TaskService service;

    @Autowired
    public TaskController(TaskService service){
        this.service = service;
    }

    @GetMapping
    public List<Task> getTasks(){
        return service.getAll();
    }
    @GetMapping(value = "{taskId}")
    public Task getTask(@PathVariable long taskId){
        return service.getTask(taskId);
    }

}
