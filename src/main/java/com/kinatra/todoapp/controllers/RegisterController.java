package com.kinatra.todoapp.controllers;

import com.kinatra.todoapp.auth.UserDao;
import com.kinatra.todoapp.auth.UserExistsException;
import com.kinatra.todoapp.auth.UserService;
import com.kinatra.todoapp.models.UserLoginPass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegisterController {
    private UserService userService;

    @Autowired
    public RegisterController(UserService userService){
        this.userService = userService;
    }
    @PostMapping
    public ResponseEntity<String> register(@RequestBody UserLoginPass user){
        try {
            //perform registration
            this.userService.saveUser(user.getLogin(), user.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\":\"successful\"}");
        }catch (UserExistsException e){
            //if user with given name exists
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\":\"User already exists\"}");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("{\"message\":\"Server error happened:" + e.getMessage() + "\"}");

        }
    }
}
