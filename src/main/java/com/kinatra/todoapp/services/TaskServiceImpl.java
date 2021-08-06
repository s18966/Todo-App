package com.kinatra.todoapp.services;

import com.kinatra.todoapp.models.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Override
    public List<Task> getAll() {
        return null;
    }

    @Override
    public Task getTask(long id) {
        return null;
    }

    @Override
    public void createTask(Task t) {

    }

    @Override
    public void updateTask(Task t) {

    }

    @Override
    public void deleteTask(long id) {

    }
}
