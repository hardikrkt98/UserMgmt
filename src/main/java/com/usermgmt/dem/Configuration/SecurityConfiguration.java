package com.usermgmt.dem.Configuration;

import com.usermgmt.dem.constants.SecurityConstant;
import com.usermgmt.dem.fitler.JwtAccessDeniedHandler;
import com.usermgmt.dem.fitler.JwtAuthenticationEntryPoint;
import com.usermgmt.dem.fitler.JwtAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {



    @Autowired
    private JwtAuthorizationFilter jwtAuthorizationFilter;
    @Autowired
    private JwtAccessDeniedHandler jwtAccessDeniedHandler;
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Autowired
    @Qualifier("UserDetailsService")
    private UserDetailsService userDetailsService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http.csrf().disable().cors().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests().antMatchers(SecurityConstant.PUBLIC_URLS).permitAll()
      .anyRequest().authenticated()
      .and().exceptionHandling().accessDeniedHandler(jwtAccessDeniedHandler)
      .and()
      .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);


    }
}
