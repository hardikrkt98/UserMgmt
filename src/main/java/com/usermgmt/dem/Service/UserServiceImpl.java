package com.usermgmt.dem.Service;

import com.usermgmt.dem.domain.User;
import com.usermgmt.dem.domain.UserPrincipal;
import com.usermgmt.dem.repository.UserRepository;
import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
@Qualifier("UserDetailsService")
public class UserServiceImpl  implements Userservice,UserDetailsService,,{
    @Autowired
    private UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user==null)
        {
            throw new UsernameNotFoundException("User not found for username"+username);

        }
        else
        {
            user.setLastLoginDisplay(user.getLastLoginDate());
            user.setLastLoginDate(new Date());
            userRepository.save(user);

            UserPrincipal userPrincipal = new UserPrincipal(user);
            return userPrincipal;
        }




    }
}
