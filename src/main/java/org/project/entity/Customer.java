package org.project.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.project.entity.enumeration.UserStatus;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Customer extends Person{
    public Customer(Integer id, LocalDateTime Time, String firstName,
                    String lastName, String email, String password,
                    UserStatus status) {
        super(id, Time, firstName, lastName, email, password, status);
    }
    @OneToMany
    private List<Order> orders;
    @OneToOne
    private Budget budget;
}
