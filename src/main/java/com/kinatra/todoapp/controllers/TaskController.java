package com.kinatra.todoapp.controllers;

import com.kinatra.todoapp.models.Task;
import com.kinatra.todoapp.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        //get all tasks
        return service.getAll();
    }
    @GetMapping(value = "{taskId}")
    public ResponseEntity<Task> getTask(@PathVariable long taskId){
        //find task by id
        try {
            Task t = service.getTask(taskId);
            return ResponseEntity.status(HttpStatus.OK).body(t);
        }catch (IllegalStateException e){
            //in case there is no task
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @PostMapping
    public ResponseEntity<String> addTask(@RequestBody Task t){
        //create task
        service.createTask(t);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> modifyTask(@RequestBody Task t){
        //update task
        service.updateTask(t);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping(value = "{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable long taskId){
        //delete task
        service.deleteTask(taskId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
