package com.backend.assignment.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // Endpoints accessible only by ROLE_COURSE_CREATOR
                .antMatchers(HttpMethod.POST, "/course/create").hasRole("COURSE_CREATOR")
                .antMatchers(HttpMethod.PUT, "/course/update/**").hasRole("COURSE_CREATOR")
                .antMatchers(HttpMethod.DELETE, "/course/delete/**").hasRole("COURSE_CREATOR")
                .antMatchers(HttpMethod.GET, "/course/{courseId}").hasRole("COURSE_CREATOR")
                // Endpoints accessible by ROLE_TEACHER, ROLE_STUDENT, and ROLE_COURSE_CREATOR
                .antMatchers(HttpMethod.GET, "/course/all").hasAnyRole("TEACHER", "STUDENT", "COURSE_CREATOR")
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("creator").password(encoder().encode("creator")).roles("COURSE_CREATOR")
                .and()
                .withUser("teacher").password(encoder().encode("teacher")).roles("TEACHER")
                .and()
                .withUser("student").password(encoder().encode("student")).roles("STUDENT");
    }

    @Bean
    public PasswordEncoder encoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}


