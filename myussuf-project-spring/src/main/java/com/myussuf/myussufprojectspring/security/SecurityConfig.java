package com.myussuf.myussufprojectspring.security;

import com.myussuf.myussufprojectspring.security.userDetailsServices.AdminDetailService;
import com.myussuf.myussufprojectspring.security.userDetailsServices.ParentDetailService;
import com.myussuf.myussufprojectspring.security.userDetailsServices.TeacherDetailService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private AdminDetailService adminDetailService;
    private TeacherDetailService teacherDetailService;
    private ParentDetailService parentDetailService;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("Mohamed")
                .password(passwordEncoder.encode("123"))
                .authorities("USER_ADMIN");

        auth.userDetailsService(adminDetailService).passwordEncoder(passwordEncoder);
        auth.userDetailsService(parentDetailService).passwordEncoder((passwordEncoder));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().antMatchers("/student/**")
                    .hasAnyAuthority("ROLE_PARENT")
                    .antMatchers("/**").permitAll();

            http.formLogin();
            http.httpBasic();
            http.csrf().disable();
    }

}
