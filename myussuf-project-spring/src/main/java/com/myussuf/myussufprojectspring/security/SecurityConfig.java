package com.myussuf.myussufprojectspring.security;

import com.myussuf.myussufprojectspring.Services.AdminServImpl;
import com.myussuf.myussufprojectspring.Services.ParentServImpl;
import com.myussuf.myussufprojectspring.Services.TeacherServImpl;
import com.myussuf.myussufprojectspring.security.userDetailsServices.AdminDetailService;
import com.myussuf.myussufprojectspring.security.userDetailsServices.CustomAuthFilter;
import com.myussuf.myussufprojectspring.security.userDetailsServices.ParentDetailService;
//import com.myussuf.myussufprojectspring.security.userDetailsServices.TeacherDetailService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private AdminServImpl adminServ;
    private TeacherServImpl teacherServ;
    private ParentServImpl parentServ;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("Mohamed")
                .password(passwordEncoder.encode("123"))
                .authorities("USER_ADMIN");

        auth.userDetailsService(teacherServ).passwordEncoder(passwordEncoder);
        auth.userDetailsService(parentServ).passwordEncoder(passwordEncoder);
        auth.userDetailsService(adminServ).passwordEncoder(passwordEncoder);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().antMatchers("/student/**")
                    .hasAnyAuthority("ROLE_TEACHER")
                    .antMatchers("/management").hasAnyAuthority("ROLE_ADMIN")
                    .antMatchers("/**").permitAll();

        http.sessionManagement().sessionCreationPolicy(STATELESS);
            http.addFilter(new CustomAuthFilter(authenticationManagerBean()));
            http.formLogin();
            http.httpBasic();
            http.csrf().disable();


    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.httpFirewall(allowUrlEncodedSlashHttpFirewall());
    }

    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        return new DefaultHttpFirewall();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
