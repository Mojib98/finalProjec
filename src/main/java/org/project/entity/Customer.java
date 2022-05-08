package org.project.entity;

import lombok.*;
import org.project.entity.enumeration.UserStatus;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Customer extends User {
    @OneToMany(mappedBy = "customers")
    private List<Order> orders;
    private Integer budget;


    public Customer(Integer id, LocalDateTime time, String firstName, String lastName, String email, String password, UserStatus status) {
        super(id, time, firstName, lastName, email, password, status);
    }

}
