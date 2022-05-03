package org.project.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.project.entity.enumeration.Statuses;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Customer extends Person{
    public Customer(Integer id, LocalDateTime Time, String firstName,
                    String lastName, String email, String password,
                    Statuses status) {
        super(id, Time, firstName, lastName, email, password, status);
    }
}
