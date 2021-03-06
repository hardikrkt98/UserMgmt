package com.usermgmt.dem.resource.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import static  com.usermgmt.dem.constants.SecurityConstant.*;
import static java.util.Arrays.stream;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.usermgmt.dem.domain.UserPrincipal;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;



@Component
public class JWTTokenProvider {
    @Value("${jwt.secret}")//will come from YAML file
    private String secret;


    public String generateJWTToken(UserPrincipal userPrincipal)
    {

      String[] claims = getClaimsFromUser(userPrincipal);
      return JWT.create().withIssuer(GET_ARRAYS_LLC).withAudience(GET_ARRAYS_ADMINISTRATION)
                            .withIssuedAt(new Date()).withSubject(userPrincipal.getUsername())
              .withArrayClaim(AUTHORITIES,claims).withExpiresAt(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
              .sign(Algorithm.HMAC512(secret));



    }

    public List<GrantedAuthority> getAuthorities(String token)
    {
        String[] claims = getClaimsFromToken(token);
        return stream(claims).map(SimpleGrantedAuthority::new).collect(Collectors.toList());



    }

    private String[] getClaimsFromToken(String token) {

        JWTVerifier jwtVerifier =  getVerifier();
        return jwtVerifier.verify(token).getClaim(AUTHORITIES).asArray(String.class);


    }

    private JWTVerifier getVerifier() {

        JWTVerifier jwtVerifier;
        try{
            Algorithm algorithm = Algorithm.HMAC512(secret);
            jwtVerifier = JWT.require(algorithm).withIssuer(GET_ARRAYS_LLC).build();


        }
        catch(JWTVerificationException exception){
           throw new JWTVerificationException(TOKEN_CANNOT_BE_VERIFIED);
         }
        return jwtVerifier;

    }

    public Authentication getAuthentication(String username, List<GrantedAuthority> authorities, HttpServletRequest request)
    {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username,null,authorities);
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
           return usernamePasswordAuthenticationToken;
    }

    public Boolean  isTokenValid(String username,String token)
    {
        JWTVerifier jwtVerifier =  getVerifier();
        return StringUtils.isNotEmpty(username)&&!isTokenExpired(jwtVerifier,token);



    }

    private boolean isTokenExpired(JWTVerifier jwtVerifier, String token) {
        Date expiration = jwtVerifier.verify(token).getExpiresAt();
        return expiration.before(new Date());
    }
   public String getSubject(String token)
   {
       JWTVerifier jwtVerifier = getVerifier();
       return jwtVerifier.verify(token).getSubject();


   }

    private String[] getClaimsFromUser(UserPrincipal userPrincipal) {
        List<String> authorities = new ArrayList<>();
        for(GrantedAuthority grantedauthority: userPrincipal.getAuthorities())
        {
            authorities.add(grantedauthority.getAuthority());
        }


        //return authorites in form of string
        return authorities.toArray(new String[0]);




    }


}
