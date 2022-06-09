package com.finalProject.Project.config;

import com.finalProject.Project.entity.enumeration.Role;
import com.finalProject.Project.service.imp.UserDetailService;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@Configuration
@AllArgsConstructor
@EnableGlobalMethodSecurity(jsr250Enabled = true,securedEnabled = true,prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    UserDetailService userDetailService;
//    private MyBasicAuthenticationEntryPoint authenticationEntryPoint;
    Success success;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//
//                .cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
//                .and()
//                .cors().configurationSource(corsConfigurationSource()).and()
                .csrf().disable()
                .authorizeRequests().antMatchers("/", "index", "/css/*", "/js/*","**.js","**.css","**.html","/**").permitAll()

//                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//                .antMatchers("/user/**").authenticated() // means what ever that begins with /api
//                .antMatchers(HttpMethod.GET, "/milad").authenticated() // means exactly /milad endpoint with HTTP method GET
                .antMatchers("/user/**").hasAnyRole("ADMIN")
                .and()
                .formLogin()
                .successHandler(success);
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
                .authorities("ROLE_ADMIN").
                and()
                .passwordEncoder(bCryptPasswordEncoder());
    }

//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("http://localhost:63342/"));
//        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
//        configuration.setAllowCredentials(true);
//        //the below three lines will add the relevant CORS response headers
////        configuration.addAllowedOrigin("*");
////        configuration.addAllowedHeader("*");
////        configuration.addAllowedMethod("*");
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }


}






