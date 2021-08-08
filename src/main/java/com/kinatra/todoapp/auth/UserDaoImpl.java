package com.kinatra.todoapp.auth;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao{

    private List<UserDetails> usersDb;
    private PasswordEncoder encoder;

    @Autowired
    public UserDaoImpl(PasswordEncoder encoder){
        this.encoder = encoder;
        usersDb = new ArrayList<>();
    }
    @Override
    public Optional<UserDetails> selectUserByUsername(String username) {
        return usersDb.stream().filter(
                userDetails -> userDetails.getUsername().equals(username)
        ).findFirst();
    }
    public List<UserDetails> getAllUsers(){
        return this.usersDb;
    }
    public void addUser(String username, String password) throws UserExistsException{
        Optional<UserDetails> userInDb = selectUserByUsername(username);
        if(userInDb.isPresent()){
            throw new UserExistsException("User already exists in db!");
        }else{
            UserDetails newUser = User.builder().username(username).password(encoder.encode(password)).roles("TASKMANAGER").build();
            usersDb.add(newUser);
        }
    }
}
