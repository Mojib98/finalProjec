package org.project.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Service extends BaseClass{
    @Column(nullable = false,unique = true)
    private String name;
    private Double upperPrice;
    private Double lowerPrice;
    @ManyToOne
    private Service category;

    public Service(Long id, LocalDateTime Time, String name, Double upperPrice, Double lowerPrice, Service category) {
        super(id, Time);
        this.name = name;
        this.upperPrice = upperPrice;
        this.lowerPrice = lowerPrice;
        this.category = category;
    }

    @OneToMany(mappedBy = "category")
    private List<Service> service;


}
