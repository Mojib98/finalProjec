package org.project.entity;

import lombok.NoArgsConstructor;
import org.project.entity.enumeration.statuses;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class Specialist extends Person{
    public Specialist(String firstName, String lastName, String email, String password, statuses status) {
        super(firstName, lastName, email, password, status);
    }

}
