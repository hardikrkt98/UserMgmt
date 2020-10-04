package com.usermgmt.dem.constants;

public class SecurityConstant {
    public static final long EXPIRATION_TIME = 432000000;//5 days in miliseconds
    public static final String TOKEN_HEADER = "Bearer";
    public static final String JWT_TOKEN_HEADER = "Jwt-Token";
    public static final String TOKEN_CANNOT_BE_VERIFIED = "Token cannot be verified";
    public static final String GET_ARRAYS_LLC = "Get Arraus LLC";
    public static final String GET_ARRAYS_ADMINISTRATION = "User Management Portal";
    public static final String AUTHORITIES = "Authorities";
    public static final String FORBIDDEN_MESSAGE = "You need to login to access this page";
    public static final String ACCESS_DENIED_MESSAGE = "You don't have permission to access this page. ";
    public static final String OPTIONS_HTTP_METHOD = "OPTIONS";
    public static final String[] PUBLIC_URLS = new String[]{"/user/login", "/user/register", "/user/resetpassword/**", "/user/image"};
}

