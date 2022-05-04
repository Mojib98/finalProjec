package org.project.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@SqlResultSetMapping(
        name = "orders",
        classes = @ConstructorResult(
                targetClass = Orders.class,
                columns = {
                        @ColumnResult(name = "id",type = Integer.class),
                        @ColumnResult(name = "time",type = LocalDateTime.class),
                        @ColumnResult(name = "offerprice",type = Double.class),
                        @ColumnResult(name = "timeforwork",type = LocalDateTime.class)})

)
@Entity
@NoArgsConstructor
@ToString
public class Orders extends BaseClass {
    private Double offerPrice;
    private LocalDateTime timeForWork;
    @ManyToOne
    private Customer customers;
    @ManyToOne
    private Service service;
    @OneToMany(mappedBy = "order")
    private List<Offer> offers;
    @Transient
    private Integer customers_id;

    /*public Orders(Integer id, LocalDateTime Time, Double offerPrice, LocalDateTime timeForWork, Customer customers, Service service) {
        super(id, Time);
        this.offerPrice = offerPrice;
        this.timeForWork = timeForWork;
        this.customers = customers;
        this.service = service;
    }*/

    public Orders(Integer id, LocalDateTime Time, Double offerPrice, LocalDateTime timeForWork) {
        super(id, Time);
        this.offerPrice = offerPrice;
        this.timeForWork = timeForWork;

    }

    public Orders(Integer id, LocalDateTime Time) {
        super(id, Time);
    }
}
