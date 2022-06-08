package com.finalProject.Project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    private UserRepository userRepository;

//    public SecurityConfig(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/**").authenticated() // means what ever that begins with /api
                .antMatchers(HttpMethod.GET, "/milad").authenticated() // means exactly /milad endpoint with HTTP method GET
                .anyRequest().permitAll()
                .and()
                .formLogin();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .userDetailsService(username ->
//                        userRepository
//                                .findByUsername(username)
//                                .orElseThrow(() -> new UsernameNotFoundException("Username#" + username + " not found!"))
//                )
//                .passwordEncoder(bCryptPasswordEncoder());
//    }

//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("Milad")
                .password("MMMMMMMMMM")
                .authorities("USER")
                .and()
                .withUser("Javid")
                .password("Rafie")
                .authorities("USER")
                .and()
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }*/
}
