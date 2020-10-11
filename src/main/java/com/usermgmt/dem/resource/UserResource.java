package com.usermgmt.dem.resource;
import com.usermgmt.dem.Exceptions.domain.EmailExistException;
import com.usermgmt.dem.Exceptions.domain.ExceptionHandling;
import com.usermgmt.dem.Exceptions.domain.UsernameExistException;
import com.usermgmt.dem.Exceptions.domain.UsernameNotFoundException;
import com.usermgmt.dem.Service.Userservice;
import com.usermgmt.dem.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = {"/","/user"})
public class UserResource extends ExceptionHandling {

    @Autowired
    private Userservice userservice;



    @PostMapping("/register")
   public ResponseEntity<User> registerUser(@RequestBody User user) throws UsernameNotFoundException, EmailExistException, UsernameExistException {
      User loginUser =   userservice.register(user.getFirstName(),user.getLastName(),user.getUsername(),user.getEmail());
     return new ResponseEntity<User>(loginUser,HttpStatus.OK);
    }

}
