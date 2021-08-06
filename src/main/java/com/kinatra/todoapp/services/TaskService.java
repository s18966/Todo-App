package com.kinatra.todoapp.services;

import com.kinatra.todoapp.models.Task;


import java.util.List;

public interface TaskService {
    List<Task> getAll();
    Task getTask(long id);
    void createTask(Task t);
    void updateTask(Task t);
    void deleteTask(long id);
}
