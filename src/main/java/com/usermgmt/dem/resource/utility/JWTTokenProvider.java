package com.usermgmt.dem.resource.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import static  com.usermgmt.dem.constants.SecurityConstant.*;
import com.usermgmt.dem.domain.UserPrincipal;
import lombok.Value;

import java.util.Date;

public class JWTTokenProvider {

    @Value("jwt.secret") //will come from YAML file
    private String secret;


    public String generateJWTToken(UserPrincipal userPrincipal)
    {

      String[] claims = getClaimsFromUser(userPrincipal);
      return JWT.create().withIssuer(GET_ARRAYS_LLC).withAudience(GET_ARRAYS_ADMINISTRATION)
                            .withIssuedAt(new Date()).withSubject(userPrincipal.getUsername())
              .withArrayClaim(AUTHORITIES,claims).withExpiresAt(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
              .sign(Algorithm.HMAC512(secret));



    }

    private String[] getClaimsFromUser(UserPrincipal userPrincipal) {
    }


}
