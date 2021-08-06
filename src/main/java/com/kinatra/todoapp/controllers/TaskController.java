package com.kinatra.todoapp.controllers;

import com.kinatra.todoapp.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {
    TaskService service;

    @Autowired
    public TaskController(TaskService service){
        this.service = service;
    }
}
