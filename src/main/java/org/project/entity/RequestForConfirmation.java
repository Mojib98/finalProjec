package org.project.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.project.entity.enumeration.Statuses;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@Data
public class RequestForConfirmation extends Person{
    @Column(length = 300)
    private String aboutMe;
    public RequestForConfirmation(String firstName, String lastName, String email, String password, Statuses status,String service) {
        super(firstName, lastName, email, password, Statuses.AWAITING_CONFIRMATION);

    }
}
