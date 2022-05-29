package com.finalProject.Project.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
@Data
@Entity
@NoArgsConstructor
@Check(constraints = "basePrice > 0")
/*select new com.finalProject.Project.entity.Order(o.id,o.time,o.offerPrice,o.timeForWork,o.address,
        o.describe,o.customers,o.Service)
        from Order o inner join SubService p on o.Service.id=p.id
        inner join Specialty s on s.service.id=p.category.id
        where s.expert.id=19*/
//@Table(name = "sub_service")
public class SubService extends BaseClass {
    @Column(unique = true,nullable = false)
    private String name;
    @NotNull
    private Integer basePrice;
    @NotNull
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
