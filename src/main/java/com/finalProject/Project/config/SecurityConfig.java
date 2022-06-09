package com.finalProject.Project.config;

import com.finalProject.Project.service.imp.UserDetailService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@AllArgsConstructor
@EnableGlobalMethodSecurity(jsr250Enabled = true,securedEnabled = true,prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    UserDetailService userDetailService;
//    private MyBasicAuthenticationEntryPoint authenticationEntryPoint;
    SuccessHandler success;

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
                .antMatchers("/customer/**").hasAnyRole("CUSTOMER")
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






