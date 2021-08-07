package com.kinatra.todoapp.services;

import com.kinatra.todoapp.models.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private List<Task> taskList;
    public TaskServiceImpl(){
        this.taskList = new ArrayList<>(Arrays.asList(
                new Task(1, "Need to finish project ASAP!", false),
                new Task(2, "Go to shopping with wife", false),
                new Task(3, "Go to bar with friends", true)
        ));
    }
    @Override
    public List<Task> getAll() {
        return this.taskList;
    }

    @Override
    public Task getTask(long id) {
        return taskList.stream().
                filter(t -> t.getId() == id).
                findFirst().
                orElseThrow(()-> new IllegalStateException("Task with id " + id + " does not exist"));
    }

    @Override
    public void createTask(Task t) {
        taskList.stream().
                filter(task -> task.getId() == t.getId()).
                findAny().orElseGet(()-> {
                    taskList.add(t);
                    return t;
                });

    }

    @Override
    public void updateTask(Task t) {
        //change values if present task
        taskList.stream().
                filter(task -> task.getId() == t.getId()).
                findFirst().ifPresent(task -> {
                    task.setDescription(t.getDescription());
                    task.setisDone(t.getisDone());
        });
    }

    @Override
    public void deleteTask(long id) {
        //delete task if present
        taskList.stream().
                filter(task -> task.getId() == id).findFirst().ifPresent(task->{
                    taskList.remove(task);
        });
    }
}
