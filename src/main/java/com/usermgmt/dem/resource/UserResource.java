package com.usermgmt.dem.resource;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserResource {
    @GetMapping("/home")
    public  String showUser(){

        return "Application works";


    }

    @GetMapping("/error")
    public  String error(){

        return "Application doesn't works";


    }



}
