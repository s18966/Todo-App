package com.kinatra.todoapp.controllers;


import com.kinatra.todoapp.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    private UserService userService;

    @Autowired
    public LoginController(UserService service){
        this.userService = service;
    }
}
