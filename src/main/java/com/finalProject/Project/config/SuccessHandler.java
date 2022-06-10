package com.finalProject.Project.config;

import com.finalProject.Project.entity.Customer;
import com.finalProject.Project.entity.User;
import com.finalProject.Project.entity.enumeration.Role;
import lombok.Getter;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
@Component
@Getter
public class SuccessHandler implements AuthenticationSuccessHandler {
    Customer authCustomer = null;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        var authUser =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            var user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Customer authCustomer = null;
        var userq = SecurityContextHolder.getContextHolderStrategy();
//            Integer i = 0;
//        if (user != null && i != 0 ) {
//            return;
//        } else {
//        HttpServletResponse res = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        String redirectUrl = null;

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            System.out.println("role " + grantedAuthority.getAuthority());
            if (grantedAuthority.getAuthority().equals(Role.EXPERT.name())) {
                redirectUrl = "C";
//                    response.addHeader("Access-Control-Expose-Headers",user.);

                break;
            } else if (grantedAuthority.getAuthority().equals("ROLE_CUSTOMER")) {
                request.authenticate(response);
                    redirectUrl = "CustomerView.html";
                    break;
                }
            }
        response.setStatus(HttpServletResponse.SC_OK);

            System.out.println("redirectUrl " + redirectUrl);
            if (redirectUrl == null) {
                throw new IllegalStateException();
            }

            new DefaultRedirectStrategy().sendRedirect(request, response, redirectUrl);
        }
    }
//}
