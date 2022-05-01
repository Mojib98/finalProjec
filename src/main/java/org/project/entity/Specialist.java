package org.project.entity;

import lombok.NoArgsConstructor;
import org.project.entity.enumeration.Statuses;

import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class Specialist extends Person{
    @Embedded
    private Avatar avatar;
    public Specialist(String firstName, String lastName, String email, String password, Statuses status) {
        super(firstName, lastName, email, password, status);
    }

}
