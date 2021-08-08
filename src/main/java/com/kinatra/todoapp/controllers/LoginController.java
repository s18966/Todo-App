package com.kinatra.todoapp.controllers;


import com.kinatra.todoapp.auth.UserService;
import com.kinatra.todoapp.models.UserLoginPass;
import com.kinatra.todoapp.security.JwtProvider;
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
    private JwtProvider jwtProvider;
    @Autowired
    public LoginController(UserService service, JwtProvider provider){
        this.userService = service;
        this.jwtProvider = provider;
    }

    @PostMapping
    public ResponseEntity<String> login(@RequestBody UserLoginPass userLoginPass){
        try {
            UserDetails user = userService.loadUserByUserNameAndPassword(userLoginPass.getLogin(), userLoginPass.getPassword());
            if(user == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\":\"Incorrect password\"}");
            }else{
                String token = jwtProvider.genToken(user.getUsername());
                return ResponseEntity.status(HttpStatus.OK).body("{\"message\":\"Successfully logged in\"," +
                        "\"token\":\"" + token + "\"}");
            }
        }catch (UsernameNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"User with given login not found on server\"}");
        }
    }

}
