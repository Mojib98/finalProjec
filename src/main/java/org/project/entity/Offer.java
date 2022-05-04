package org.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
public class Offer extends BaseClass{
    private Double offerPrice;
    private LocalDateTime workTime;
    private Integer timeWorkPerMinute;
    @ManyToOne
    private Order order;
    @ManyToOne()
    private Specialist specialists;

    public Offer(Integer id, LocalDateTime Time, Double offerPrice, LocalDateTime workTime, Integer timeWorkPerMinute, Order order) {
        super(id, Time);
        this.offerPrice = offerPrice;
        this.workTime = workTime;
        this.timeWorkPerMinute = timeWorkPerMinute;
        this.order = order;
    }

    public Offer(Integer id, LocalDateTime Time, Double offerPrice, LocalDateTime workTime, Integer timeWorkPerMinute, Order order, Specialist specialists) {
        super(id, Time);
        this.offerPrice = offerPrice;
        this.workTime = workTime;
        this.timeWorkPerMinute = timeWorkPerMinute;
        this.order = order;
        this.specialists = specialists;
    }

    public Offer() {

    }
}
