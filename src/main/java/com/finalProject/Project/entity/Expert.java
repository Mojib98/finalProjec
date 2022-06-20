package com.finalProject.Project.entity;

import com.finalProject.Project.entity.enumeration.Role;
import com.finalProject.Project.entity.enumeration.UserStatus;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Expert extends User  implements UserDetails {
    @Embedded
    private Avatar avatar;
    @Column(columnDefinition = "integer default 0")
    private Integer wallet;
    @Column(columnDefinition = "integer default 5")
    private Float rate;
    public Expert(Integer id, LocalDateTime time, String firstName, String lastName, String email, String password, UserStatus status, Avatar avatar) {
        super(id, time, firstName, lastName, email, password, status);
        this.avatar = avatar;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(Role.EXPERT.name()));
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return getStatus().equals(UserStatus.ACTIVE);
    }

    @Override
    public boolean isAccountNonLocked() {
        return getStatus().equals(UserStatus.ACTIVE);
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
