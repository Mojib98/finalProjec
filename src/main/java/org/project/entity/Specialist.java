package org.project.entity;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class Specialist extends Person{
    public Specialist(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password);
    }
}
