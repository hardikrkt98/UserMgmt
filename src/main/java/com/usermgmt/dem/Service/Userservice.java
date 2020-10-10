package com.usermgmt.dem.Service;

import com.usermgmt.dem.Exceptions.domain.EmailExistException;
import com.usermgmt.dem.Exceptions.domain.UsernameExistException;
import com.usermgmt.dem.Exceptions.domain.UsernameNotFoundException;
import com.usermgmt.dem.domain.User;

import java.util.List;

public interface Userservice {

    User register(String firstName,String lastName,String userName,String email) throws UsernameNotFoundException, EmailExistException, UsernameExistException;

    List<User> getUsers();
    User findUserByUsername(String username);

    User findUserByEmail(String email);




}
