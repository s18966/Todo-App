package com.kinatra.todoapp.models;

//needed for registration and login
public class UserLoginPass {
    private String login;
    private String password;
    public UserLoginPass(String login, String password){
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
    public String toString(){
        return "{\"login\":\"" + this.login + "\", \"password\":" + this.password + "\"}";
    }

}
