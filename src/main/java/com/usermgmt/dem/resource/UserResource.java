package com.usermgmt.dem.resource;
import com.usermgmt.dem.Exceptions.domain.EmailExistException;
import com.usermgmt.dem.Exceptions.domain.ExceptionHandling;
import com.usermgmt.dem.Exceptions.domain.UsernameExistException;
import com.usermgmt.dem.Exceptions.domain.UsernameNotFoundException;
import com.usermgmt.dem.Service.Userservice;
import com.usermgmt.dem.domain.ArrayResponse;
import com.usermgmt.dem.domain.User;
import com.usermgmt.dem.domain.UserPrincipal;
import com.usermgmt.dem.domain.arrays;
import com.usermgmt.dem.repository.ArraysRepository;
import com.usermgmt.dem.resource.utility.JWTTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

import java.util.List;

import static com.usermgmt.dem.constants.SecurityConstant.JWT_TOKEN_HEADER;

@RestController
@RequestMapping(path = {"/","/user"})
public class UserResource extends ExceptionHandling {

    @Autowired
    private Userservice userservice;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTTokenProvider jwtTokenProvider;

    @Autowired
    private ArraysRepository arraysRepository;



    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) throws UsernameNotFoundException, EmailExistException, UsernameExistException {
        authenticate(user.getUsername(),user.getPassword());
        User loginUser = userservice.findUserByUsername(user.getUsername());
        UserPrincipal userPrincipal = new UserPrincipal(loginUser);
        HttpHeaders jwtheader = getjwtHeader(userPrincipal);
        return new ResponseEntity<User>(loginUser,jwtheader,HttpStatus.OK);
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
    }

    private HttpHeaders getjwtHeader(UserPrincipal userPrincipal) {

      String header =  jwtTokenProvider.generateJWTToken(userPrincipal);
      HttpHeaders httpHeaders = new HttpHeaders();
      httpHeaders.add(JWT_TOKEN_HEADER,header);
      return httpHeaders;


    }

    @GetMapping("/arrays")
    public ResponseEntity<ArrayResponse> getArraysProblems()
    {
        List<arrays> response =  arraysRepository.findAll();
      ArrayResponse arrayResponse = new ArrayResponse(response);
        arrayResponse.setArraysList(response);
        return new ResponseEntity<ArrayResponse>(arrayResponse,HttpStatus.OK);
    }

    @GetMapping("/problem/{title}")
    public  ResponseEntity<arrays>getProblemStatement( @PathVariable String title)
    {
        arrays response = arraysRepository.findBytitle(title);

        return new ResponseEntity<arrays>(response,HttpStatus.OK);


    }


    @PostMapping("/register")
   public ResponseEntity<User> registerUser(@RequestBody User user) throws UsernameNotFoundException, EmailExistException, UsernameExistException, MessagingException {
      User loginUser =   userservice.register(user.getFirstName(),user.getLastName(),user.getUsername(),user.getEmail());
     return new ResponseEntity<User>(loginUser,HttpStatus.OK);
    }




}
