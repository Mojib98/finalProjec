package org.project.entity;

import lombok.*;
import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Service extends BaseClass{
    @Column(nullable = false,unique = true)
    private String name;
   /* @OneToMany(mappedBy = "service")
    private List<Specialty> specialty;
*/

    public Service(Integer id, LocalDateTime time, String name) {
        super(id, time);
        this.name = name;
    }
}
