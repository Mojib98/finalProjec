package com.finalProject.Project.entity;

import com.finalProject.Project.entity.enumeration.Role;
import com.finalProject.Project.entity.enumeration.UserStatus;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Setter
@Getter
@Data
@NoArgsConstructor
public class Customer extends  User implements UserDetails {
    @OneToMany(mappedBy = "customers")
    private List<Order> orders;
    private Integer wallet;


    public Customer(Integer id, LocalDateTime time, String firstName, String lastName, String email, String password, UserStatus status) {
        super(id, time, firstName, lastName, email, password, status);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "orders=" + orders +
                ", budget=" + wallet +
                "} " + super.toString();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(Role.CUSTOMER.name()));
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return (getStatus().equals(UserStatus.ACTIVE));
    }

    @Override
    public boolean isAccountNonLocked() {
        return (getStatus().equals(UserStatus.ACTIVE));
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return getStatus().equals(UserStatus.ACTIVE);
    }
}
