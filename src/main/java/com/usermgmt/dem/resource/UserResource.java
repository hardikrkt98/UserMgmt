package com.usermgmt.dem.resource;
import com.usermgmt.dem.Exceptions.domain.EmailExistException;
import com.usermgmt.dem.Exceptions.domain.ExceptionHandling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = {"/","/user"})
public class UserResource extends ExceptionHandling {
    @GetMapping("/home")
    public  String showUser() throws EmailExistException {

//        return "Application works";
        throw new EmailExistException("This Email address already taken");


    }

}
