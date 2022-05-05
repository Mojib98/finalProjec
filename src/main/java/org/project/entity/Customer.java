package org.project.entity;

import lombok.*;
import org.project.entity.enumeration.UserStatus;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class Customer extends Person{
    public Customer(Integer id, LocalDateTime Time, String firstName,
                    String lastName, String email, String password,
                    UserStatus status) {
        super(id, Time, firstName, lastName, email, password, status);
    }
    @OneToMany(mappedBy = "customers")
    private List<Order> orders;
    @OneToOne
    private Budget budget;

    public Customer(Integer id, LocalDateTime Time, String firstName, String lastName, String email, String password, UserStatus status, Budget budget) {
        super(id, Time, firstName, lastName, email, password, status);
        this.budget = budget;
    }
}
