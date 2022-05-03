package org.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.project.entity.enumeration.Statuses;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestForConfirmation extends Person {
    @Column(length = 300)
    private String aboutMe;
    @Column(unique = true,nullable = false)
    private  Integer trackingNumber;

    public RequestForConfirmation(Integer id, LocalDateTime Time, String firstName, String lastName, String email, String password, Statuses status, String aboutMe) {
        super(id, Time, firstName, lastName, email, password, status);
        this.aboutMe = aboutMe;
    }
  /*    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RequestForConfirmation request = (RequestForConfirmation) o;
        return Objects.equals(aboutMe, request.aboutMe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), aboutMe);
    }*/
}
