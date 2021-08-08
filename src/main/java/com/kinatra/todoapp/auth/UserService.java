package com.kinatra.todoapp.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService implements UserDetailsService {

    private UserDao userDao;

    @Autowired
    public UserService(UserDao dao){
        this.userDao = dao;
    }
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userDao.selectUserByUsername(s)
                .orElseThrow(()-> new UsernameNotFoundException("Username "  + s + " was not found"));
    }

    public void registerUser(String login, String password) throws UserExistsException{
        userDao.addUser(login, password);
    }

}
