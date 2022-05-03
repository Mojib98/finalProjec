package org.project.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.project.entity.enumeration.Statuses;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class Specialist extends Person{
    @Embedded
    private Avatar avatar;

    public Specialist(Integer id, LocalDateTime Time, String firstName,
                      String lastName, String email, String password, Statuses status) {
        super(id, Time, firstName, lastName, email, password, status);
    }

    @ManyToMany
    private Set<Service> services=new HashSet<>();


    public void addService(Service service){
        this.services.add(service);
    }
    @OneToOne(mappedBy = "specialist")
    private RequestForNewSpecialization request;

}
