package org.project.entity;

import lombok.*;
import org.project.entity.enumeration.UserStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RequestForNewSpecialization extends BaseClass {
    private String name;
    private String Description;
    @Enumerated(EnumType.STRING)
    private UserStatus statusUser;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Specialist specialist;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Service service;
    @Transient
   private Long idS;

    public RequestForNewSpecialization(Integer id, LocalDateTime Time, String name, String description,
                                       UserStatus statusUser, Specialist specialist, Integer ids) {
        super(id, Time);
        this.name = name;
        Description = description;
        this.statusUser = statusUser;
        this.specialist = specialist;
        this.service = new Service();
        service.setId(ids);
    }

    public RequestForNewSpecialization(Integer id, LocalDateTime Time, String name, String description, UserStatus statusUser, Specialist specialist, Service service) {
        super(id, Time);
        this.name = name;
        Description = description;
        this.statusUser = statusUser;
        this.specialist = specialist;
        this.service = service;

    }
}

