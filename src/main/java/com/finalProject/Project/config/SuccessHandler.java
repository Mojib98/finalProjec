package com.finalProject.Project.config;

import com.finalProject.Project.entity.enumeration.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
@Component
public class SuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
            var user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            var userq = SecurityContextHolder.getContext().getAuthentication();
            Integer i = 0;
        if (user != null && i != 0 ) {
            return;
        } else {
            String redirectUrl = null;

            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority grantedAuthority : authorities) {
                System.out.println("role " + grantedAuthority.getAuthority());
                if (grantedAuthority.getAuthority().equals(Role.EXPERT.name())) {
                    redirectUrl = "http://localhost:63342/Project/public/ExpertView.html";
//                    response.addCookie(userq.getCredentials());
                    i++;
                    break;
                } else if (grantedAuthority.getAuthority().equals("CUSTOMER")) {
                    redirectUrl = "http://localhost:63342/Project/public/CustomerView.html";
                    break;
                }
            }
            System.out.println("redirectUrl " + redirectUrl);
            if (redirectUrl == null) {
                throw new IllegalStateException();
            }
            new DefaultRedirectStrategy().sendRedirect(request, response, redirectUrl);
        }
    }
}
