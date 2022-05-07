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
 /*   @OneToMany(mappedBy = "service")
    private List<Specialty> specialties;*/
}
