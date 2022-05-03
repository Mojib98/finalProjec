package org.project.entity;

import lombok.*;
import org.project.entity.enumeration.Statuses;

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
    private Statuses statuses;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Specialist specialist;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Service service;
    @Transient
   private Long idS;

    public RequestForNewSpecialization(Integer id, LocalDateTime Time, String name, String description,
                                       Statuses statuses, Specialist specialist, Integer ids) {
        super(id, Time);
        this.name = name;
        Description = description;
        this.statuses = statuses;
        this.specialist = specialist;
        this.service = new Service();
        service.setId(ids);
    }

    public RequestForNewSpecialization(Integer id, LocalDateTime Time, String name, String description, Statuses statuses, Specialist specialist, Service service) {
        super(id, Time);
        this.name = name;
        Description = description;
        this.statuses = statuses;
        this.specialist = specialist;
        this.service = service;

    }
}

