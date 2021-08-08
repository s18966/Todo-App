package com.kinatra.todoapp.auth;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<UserDetails> selectUserByUsername(String username);
    List<UserDetails> getAllUsers();
    void addUser(String username, String password) throws UserExistsException;
}
