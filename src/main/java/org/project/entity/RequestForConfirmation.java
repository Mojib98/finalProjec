package org.project.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.project.entity.enumeration.Statuses;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@Data
public class RequestForConfirmation extends Person{
    public RequestForConfirmation(String firstName, String lastName, String email, String password, Statuses status) {
        super(firstName, lastName, email, password, Statuses.AWAITING_CONFIRMATION);
    }
}
