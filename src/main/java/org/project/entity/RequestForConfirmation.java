package org.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.project.entity.enumeration.UserStatus;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestForConfirmation extends User {
//    @Column(length = 300)
    private String aboutMe;
/*
    @Column(unique = true,nullable = false)
*/

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
