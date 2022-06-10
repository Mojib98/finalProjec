package com.finalProject.Project;

import com.finalProject.Project.entity.enumeration.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
@Component
public class SuccessHandler2 extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        String redirectUrl = null;

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            System.out.println("role " + grantedAuthority.getAuthority());
            if (grantedAuthority.getAuthority().equals("ROLE_CUSTOMER")) {
                redirectUrl = "CustomerView.html";
//                response.encodeRedirectURL(redirectUrl);
//
                response.sendRedirect(redirectUrl);
                response.setStatus(HttpServletResponse.SC_OK);
//setTargetUrlParameter(redirectUrl);
            }
            super.onAuthenticationSuccess(request, response, authentication);
        }


    }
}
