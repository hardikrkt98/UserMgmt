package com.usermgmt.dem.fitler;

import com.usermgmt.dem.constants.SecurityConstant;
import com.usermgmt.dem.resource.utility.JWTTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.usermgmt.dem.constants.SecurityConstant.OPTIONS_HTTP_METHOD;
import static com.usermgmt.dem.constants.SecurityConstant.TOKEN_PREFIX;


@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

     @Autowired
     JWTTokenProvider jwtTokenProvider;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        if(httpServletRequest.getMethod().equalsIgnoreCase(OPTIONS_HTTP_METHOD))
        {
            httpServletResponse.setStatus(HttpStatus.OK.value());

        }

        else
        {
          String authorizartionHeader = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
          if(authorizartionHeader==null||!authorizartionHeader.startsWith(SecurityConstant.TOKEN_PREFIX))
          {
              filterChain.doFilter(httpServletRequest,httpServletResponse);
              return;

          }
            String token = authorizartionHeader.substring(TOKEN_PREFIX.length());
            String username = jwtTokenProvider.getSubject(token);

            if(jwtTokenProvider.isTokenValid(username,token)&& SecurityContextHolder.getContext().getAuthentication()==null)
            {
                List<GrantedAuthority> authorities = jwtTokenProvider.getAuthorities(token);
                Authentication authentication = jwtTokenProvider.getAuthentication(username,authorities,httpServletRequest);
                SecurityContextHolder.getContext().setAuthentication(authentication);



            }
            else
            {
                SecurityContextHolder.clearContext();


            }


        }

        filterChain.doFilter(httpServletRequest,httpServletResponse);





    }
}
