package com.kinatra.todoapp.auth;

//needed for adding user to db
public class UserExistsException extends Exception {
    public UserExistsException(String msg){
        super(msg);
    }
}
