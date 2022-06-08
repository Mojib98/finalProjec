package com.finalProject.Project.service.imp;

import com.finalProject.Project.repository.interfaces.CustomerRepositorySecurity;
import com.finalProject.Project.repository.interfaces.ExpertRepositorySecutiry;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
@AllArgsConstructor
public class UserDetailService implements UserDetailsService {
    private final CustomerRepositorySecurity customerRepositorySecurity;
    private final ExpertRepositorySecutiry expertRepositorySecutiry;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (customerRepositorySecurity.findByEmail(username).isPresent())
            return customerRepositorySecurity.findByEmail(username)
                    .orElseThrow(()->new UsernameNotFoundException("user name not correct"));
        else
            return expertRepositorySecutiry.findByEmail(username)
                    .orElseThrow(()->new UsernameNotFoundException("user name not correct"));
    }
    }

