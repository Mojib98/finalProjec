package org.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
public class Offer extends BaseClass{
    private Double offerPrice;
    private LocalDateTime workTime;
    private Integer timeWorkPerMinute;
    @ManyToOne
    private Orders order;
    @ManyToOne()
    private Specialist specialists;

    public Offer(Integer id, LocalDateTime Time, Double offerPrice, LocalDateTime workTime, Integer timeWorkPerMinute, Orders order) {
        super(id, Time);
        this.offerPrice = offerPrice;
        this.workTime = workTime;
        this.timeWorkPerMinute = timeWorkPerMinute;
        this.order = order;
    }

    public Offer() {

    }
}
