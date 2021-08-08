package com.kinatra.todoapp.controllers;


import com.kinatra.todoapp.auth.UserService;
import com.kinatra.todoapp.models.UserLoginPass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping
    public ResponseEntity<String> login(@RequestBody UserLoginPass userLoginPass){
        try {
            UserDetails user = userService.loadUserByUserNameAndPassword(userLoginPass.getLogin(), userLoginPass.getPassword());
            if(user == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\":\"Incorrect password\"}");
            }else{
                return ResponseEntity.status(HttpStatus.OK).body("Loogged in");
            }
        }catch (UsernameNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"User with given login not found on server\"}");
        }
    }

}
