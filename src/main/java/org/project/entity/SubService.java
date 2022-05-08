package org.project.entity;

import lombok.Data;
import org.hibernate.annotations.Check;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Data
@Check(constraints = "basePrice > 0")
public class SubService extends BaseClass {
    @Column(nullable = false, unique = true)
    private String name;
    private Integer basePrice;
    private String describe;
    @ManyToOne
    private Service service;

}
