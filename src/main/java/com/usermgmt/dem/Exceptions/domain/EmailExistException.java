package com.usermgmt.dem.Exceptions.domain;


//Exception is the error that occurs during runtime execution of class
public class EmailExistException extends Exception{

    public EmailExistException(String message) {
        super(message);
    }
}
