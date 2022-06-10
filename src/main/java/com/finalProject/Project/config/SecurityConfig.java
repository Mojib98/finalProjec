package com.finalProject.Project.config;

import com.finalProject.Project.SuccessHandler2;
import com.finalProject.Project.service.imp.UserDetailService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@AllArgsConstructor
//@EnableGlobalMethodSecurity(jsr250Enabled = true,securedEnabled = true,prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    UserDetailService userDetailService;
    SuccessHandler success;
    SuccessHandler2 successHandler2;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//
//                .cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
//                .and()
//                .cors().configurationSource(corsConfigurationSource()).and()
                .csrf().disable()
                .authorizeRequests()

//                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()

                .antMatchers("/**").permitAll()
//                .antMatchers("/user/**").authenticated() // means what ever that begins with /api
//                .antMatchers(HttpMethod.GET, "/milad").authenticated() // means exactly /milad endpoint with HTTP method GET
                .antMatchers("/user/**").hasAnyRole("ADMIN")
//                .antMatchers("/customer/**").hasAnyRole("ROLE_CUSTOMER")
//                .antMatchers("/exp/**").hasAnyRole("EXPERT","ADMIN")
                .antMatchers("/service/**").hasAnyRole("CUSTOMER","ADMIN")
                .antMatchers("/singup/**").permitAll()
                .antMatchers("/customer/onlinePaying","/customer/howMuch").permitAll()
                .anyRequest().permitAll()
                .and()
                .formLogin().successHandler(success)
                ;
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

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.inMemoryAuthentication()
////                .withUser("ADMIN")
////                .password("$2a$10$4cdCfnV3DryySlu70U0cZ.WQTaeJOymtO8ogAwyS22aIai.8AQEDC")
////                .authorities("ROLE_ADMIN").
////                and()
////                .passwordEncoder(bCryptPasswordEncoder());
//        auth.userDetailsService(userDetailService)
//                .passwordEncoder(bCryptPasswordEncoder());
//    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                authenticationProvider(daoAuthenticationProvider());
                auth.inMemoryAuthentication()
                .withUser("ADMIN")
                .password("$2a$10$4cdCfnV3DryySlu70U0cZ.WQTaeJOymtO8ogAwyS22aIai.8AQEDC")
                .authorities("ROLE_ADMIN").
                and()
                .passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder());
        provider.setUserDetailsService(userDetailService);
        return provider;
    }



}






