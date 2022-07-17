/*
package com.finalProject.Project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;


@Component
class SimpleCOR implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        try {
            chain.doFilter(req, res);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void init(FilterConfig filterConfig) {}

    public void destroy() {}
//        @Bean
//        SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
//        http
//                // ...
//                .cors(cors -> cors.disable());
//        return http.build();
//    }

}
*/
