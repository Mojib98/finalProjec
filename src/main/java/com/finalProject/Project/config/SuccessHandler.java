package com.finalProject.Project.config;
import com.finalProject.Project.entity.Customer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
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
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        String redirectUrl = null;

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            System.out.println("role " + grantedAuthority.getAuthority());
            if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
                redirectUrl = "manageUser.html";
                break;
            } else if (grantedAuthority.getAuthority().equals("ROLE_CUSTOMER")) {
                    redirectUrl = "CustomerView.html";
                    break;
                }else {
                redirectUrl = "ExpertView.html";
                break;
            }

        }
        System.out.println("redirectUrl " + redirectUrl);
        if (redirectUrl == null) {
            throw new IllegalStateException();
        }
        response.setStatus(HttpServletResponse.SC_OK);

        new DefaultRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }

}
