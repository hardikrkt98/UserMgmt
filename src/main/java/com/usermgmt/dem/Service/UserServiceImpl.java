package com.usermgmt.dem.Service;

import com.usermgmt.dem.Exceptions.domain.EmailExistException;
import com.usermgmt.dem.Exceptions.domain.UsernameExistException;
import com.usermgmt.dem.domain.User;
import com.usermgmt.dem.domain.UserPrincipal;
import com.usermgmt.dem.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Random;

import static com.usermgmt.dem.enumeration.Role.ROLE_USER;


@Service
@Transactional
@Qualifier("userDetailsService")
public class UserServiceImpl  implements Userservice,UserDetailsService{


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl() {
    }

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;



//    @Autowired
////    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
////        this.userRepository = userRepository;
////        this.passwordEncoder = passwordEncoder;
////    }


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

    @Override
    public User register(String firstName, String lastName, String userName, String email) throws com.usermgmt.dem.Exceptions.domain.UsernameNotFoundException, EmailExistException, UsernameExistException {
      User user =   validateNewUsernameEmail(StringUtils.EMPTY,userName,email);
      User newUser = new User();
      newUser.setUserId(generateUserId());
      String password = generatePassword();
      String encodedPassword = bCryptPasswordEncoder.encode(password);
      newUser.setAuthorities(ROLE_USER.getAuthorities());
      newUser.setRoles(ROLE_USER.name());
      newUser.setJoinDate(new Date());
      newUser.setPassword(encodedPassword);
     newUser.setActive(true);
     newUser.setNotLocked(true);
     newUser.setProfileUrl(getProfileImageUrl());
     newUser.setFirstName(firstName);
     newUser.setLastName(lastName);
     userRepository.save(user);

    }

    private String getProfileImageUrl() {

        return null;
    }



    private String generatePassword() {
        return RandomStringUtils.randomAlphabetic(10);
    }

    private String generateUserId() {
        return RandomStringUtils.randomNumeric(10);


    }

    private User validateNewUsernameEmail(String currentUsername,String newUsername,String newEmail) throws com.usermgmt.dem.Exceptions.domain.UsernameNotFoundException, UsernameExistException, EmailExistException {

        if(StringUtils.isNotBlank(currentUsername))
        {
            User currentUser = findUserByUsername(currentUsername);
              if(currentUser==null)
              {
                  throw new com.usermgmt.dem.Exceptions.domain.UsernameNotFoundException("No user found by username"+currentUsername);

              }

              User userByusername = findUserByUsername(newUsername);
              if(userByusername!=null && currentUser.getId().equals(userByusername.getId()))
              {
                  throw new UsernameExistException("Username already exist"+newUsername);


              }
            User userByemail = findUserByEmail(newEmail);
            if(userByemail!=null && currentUser.getId().equals(userByemail.getId()))
            {
                throw new EmailExistException("Email already exist"+newEmail);


            }

          return currentUser;
        }
        else
        {
            User userByUsername = findUserByUsername(currentUsername);
            if(userByUsername!=null)
            {

                throw new UsernameExistException("username already exists"+currentUsername);

            }

            User userByEmail = findUserByEmail(newEmail);
            if(userByEmail!=null)
            {
                throw new EmailExistException("Username already Exists exception");


            }


            return null;
        }


    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public User findUserByUsername(String username) {
        return null;
    }

    @Override
    public User findUserByEmail(String email) {
        return null;
    }
}
