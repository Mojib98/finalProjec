package com.finalProject.Project.entity;

import com.finalProject.Project.entity.enumeration.UserStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@AllArgsConstructor
@Setter
@Getter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@MappedSuperclass
public class Users extends BaseClass{
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @Column(nullable = false,unique = true)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    public Users() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Users person = (Users) o;
        return Objects.equals(email, person.email);
    }

    public Users(Integer id, LocalDateTime time, String firstName, String lastName, String email, String password, UserStatus status) {
        super(id, time);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.status = status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), email);
    }
}
