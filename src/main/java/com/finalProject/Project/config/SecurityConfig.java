package com.finalProject.Project.config;

import com.finalProject.Project.entity.enumeration.Role;
import com.finalProject.Project.service.imp.UserDetailService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    UserDetailService userDetailService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
//                .antMatchers("/api/**").authenticated() // means what ever that begins with /api
//                .antMatchers(HttpMethod.GET, "/milad").authenticated() // means exactly /milad endpoint with HTTP method GET
                .anyRequest().authenticated()
                .and()
                .formLogin();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.
//                userDetailsService(userDetailService)
//                .passwordEncoder(bCryptPasswordEncoder());
//    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("ADMIN")
                .password("$2a$10$4cdCfnV3DryySlu70U0cZ.WQTaeJOymtO8ogAwyS22aIai.8AQEDC")
                .authorities(Role.ADMIN.name())
                .and()
                .withUser("Javid")
                .password("Rafie")
                .authorities("USER")
                .and()
                .passwordEncoder(bCryptPasswordEncoder());
    }
}
