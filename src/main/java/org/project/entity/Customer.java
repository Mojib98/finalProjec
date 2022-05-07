package org.project.entity;

import lombok.*;
import org.project.entity.enumeration.UserStatus;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class Customer extends User {
    @OneToMany(mappedBy = "customers")
    private List<Order> orders;
    private Integer budget;

}
