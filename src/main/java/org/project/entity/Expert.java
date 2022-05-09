package org.project.entity;

import lombok.*;
import org.project.entity.enumeration.UserStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Expert extends User {
    @Embedded
    private Avatar avatar;
    private Integer budget;

    public Expert(Integer id, LocalDateTime time, String firstName, String lastName, String email, String password, UserStatus status, Avatar avatar) {
        super(id, time, firstName, lastName, email, password, status);
        this.avatar = avatar;
    }

  /*   @OneToMany(mappedBy = "service")
    private List<Specialty> specialties;*/
}
