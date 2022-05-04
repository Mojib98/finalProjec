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
@Check(constraints = "upperPrice >= lowerPrice")
public class Service extends BaseClass{
    @Column(nullable = false,unique = true)
    private String name;
    private Double upperPrice;
    private Double lowerPrice;

    @ManyToOne(fetch = FetchType.EAGER)
    private Service category;

    public Service(Integer id, LocalDateTime Time, String name, Double upperPrice, Double lowerPrice, Service category) {
        super(id, Time);
        this.name = name;
        this.upperPrice = upperPrice;
        this.lowerPrice = lowerPrice;
        this.category = category;
    }

    public Service(Integer id, LocalDateTime Time, String name, Double upperPrice, Double lowerPrice) {
        super(id, Time);
        this.name = name;
        this.upperPrice = upperPrice;
        this.lowerPrice = lowerPrice;
    }

   /* @OneToMany(mappedBy = "category")
    private List<Service> service;*/
    @ManyToMany(mappedBy = "services",fetch = FetchType.EAGER)
    private List<Specialist> specialists;
    @OneToOne(mappedBy = "service",fetch = FetchType.EAGER)
    private RequestForNewSpecialization request;

    public Service(Integer id, LocalDateTime Time, String name) {
        super(id, Time);
        this.name = name;
    }
}
