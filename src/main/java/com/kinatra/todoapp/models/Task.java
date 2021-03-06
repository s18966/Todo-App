package com.kinatra.todoapp.models;


//POJO Task
public class Task {
    private final long id;
    private String description;
    private boolean isDone;

    public Task(long id, String description, boolean isDone){
        this.id = id;
        this.description = description;
        this.isDone = isDone;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean getisDone() {
        return isDone;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setisDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"description\":\"" + description + '\"' +
                ", \"isDone\":" + isDone +
                '}';
    }
}
