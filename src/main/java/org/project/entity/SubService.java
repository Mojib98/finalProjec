package org.project.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Check(constraints = "basePrice > 0")
public class SubService extends BaseClass {
    @Column(nullable = false, unique = true)
    private String name;
    private Integer basePrice;
    private String describe;
    @ManyToOne
    private Service service;

    public SubService(Integer id, LocalDateTime time, String name, Integer basePrice, String describe, Service service) {
        super(id, time);
        this.name = name;
        this.basePrice = basePrice;
        this.describe = describe;
        this.service = service;
    }
}
