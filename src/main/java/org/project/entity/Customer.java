package org.project.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
public class Customer extends Person{
    public Customer(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password);
    }

}
