package com.kinatra.todoapp.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService implements UserDetailsService {

    private UserDao userDao;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public UserService(UserDao dao, PasswordEncoder passwordEncoder){
        this.userDao = dao;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //try to find user
        return userDao.selectUserByUsername(s)
                .orElseThrow(()-> new UsernameNotFoundException("Username "  + s + " was not found"));
    }
    //throws exception if there is user with same username
    public void saveUser(String login, String password) throws UserExistsException{
        //save user to db
        userDao.addUser(login, password);
    }
    public UserDetails loadUserByUserNameAndPassword(String login, String password) throws UsernameNotFoundException {
        //retrieve user
        UserDetails user = loadUserByUsername(login);
        //check if passwords match
        if (passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }
}
