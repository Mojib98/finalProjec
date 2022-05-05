package org.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
@SqlResultSetMapping(
        name = "offer",
        classes = @ConstructorResult(
                targetClass = Offer.class,
                columns = {
                        @ColumnResult(name = "id",type = Integer.class),
                        @ColumnResult(name = "time",type = LocalDateTime.class),
                        @ColumnResult(name = "offerprice",type = Double.class),
                        @ColumnResult(name = "worktime",type = LocalDateTime.class),
                        @ColumnResult(name = "order_id",type = Order.class),
                        @ColumnResult(name = "specialists_id",type = Specialist.class)

                })

)
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
    @Transient
    private Integer idOrder;
    @Transient
    private Integer idSpecialty;


    public Offer(Integer id, LocalDateTime Time, Double offerPrice, LocalDateTime workTime, Integer timeWorkPerMinute, Order order) {
        super(id, Time);
        this.offerPrice = offerPrice;
        this.workTime = workTime;
        this.timeWorkPerMinute = timeWorkPerMinute;
        this.order = order;
    }

    public Offer(Integer id, LocalDateTime Time, Double offerPrice, LocalDateTime workTime, Integer timeWorkPerMinute, Integer order, Integer specialists) {
        super(id, Time);
        this.offerPrice = offerPrice;
        this.workTime = workTime;
        this.timeWorkPerMinute = timeWorkPerMinute;
        this.order = new Order();
        this.order.setId(order);
        this.specialists = new Specialist();
        this.specialists.setId(specialists);
    }

    public Offer() {

    }

    public Offer(Integer id, LocalDateTime time, Double offerPrice, LocalDateTime workTime, Integer idOrder, Integer idSpecialty) {
        super(id, time);
        this.offerPrice = offerPrice;
        this.workTime = workTime;
        this.order = new Order();
        order.setId(idOrder);
        this.specialists = new Specialist();
        specialists.setId(idSpecialty);
    }

    public Offer(Integer id, LocalDateTime time, Double offerPrice, LocalDateTime workTime, Integer timeWorkPerMinute, Order order, Specialist specialists) {
        super(id, time);
        this.offerPrice = offerPrice;
        this.workTime = workTime;
        this.timeWorkPerMinute = timeWorkPerMinute;
        this.order = order;
        this.specialists = specialists;
    }
}
