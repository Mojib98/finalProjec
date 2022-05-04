package org.project.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.project.entity.enumeration.UserStatus;
import org.project.entity.enumeration.WorkStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@SqlResultSetMapping(
        name = "orders",
        classes = @ConstructorResult(
                targetClass = Order.class,
                columns = {
                        @ColumnResult(name = "id",type = Integer.class),
                        @ColumnResult(name = "time",type = LocalDateTime.class),
                        @ColumnResult(name = "offerprice",type = Double.class),
                        @ColumnResult(name = "timeforwork",type = LocalDateTime.class),
                        @ColumnResult(name = "describe",type = String.class),
                        @ColumnResult(name = "customers_id",type = Customer.class)

                })

)
@Entity
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Table(name = "orders")
public class Order extends BaseClass {
    private Double offerPrice;
    private LocalDateTime timeForWork;
    private String address;
    private String describe;
    @Enumerated(EnumType.STRING)
    private WorkStatus workStatus;
    @ManyToOne
    private Customer customers;
    @ManyToOne
    private Service service;
    @OneToMany(mappedBy = "order")
    private List<Offer> offers;
    @OneToOne
    private AcceptOffer acceptOffer;

    public Order(Integer id, LocalDateTime Time, Double offerPrice, LocalDateTime timeForWork) {
        super(id, Time);
        this.offerPrice = offerPrice;
        this.timeForWork = timeForWork;

    }

    public Order(Integer id, LocalDateTime Time, Double offerPrice, LocalDateTime timeForWork, Customer customers, Service service) {
        super(id, Time);
        this.offerPrice = offerPrice;
        this.timeForWork = timeForWork;
        this.customers = customers;
        this.service = service;
    }

    public Order(Integer id, LocalDateTime Time) {
        super(id, Time);
    }

    @Override
    public String toString() {
        return "Orders{" +
                "offerPrice=" + offerPrice +
                ", timeForWork=" + timeForWork +
                ", customers=" + customers +
                ", service=" + service +
                "} " + super.toString();
    }

    public Order(Integer id, LocalDateTime time, Double offerPrice, LocalDateTime timeForWork, String describe, Integer customers) {
        super(id, time);
        this.offerPrice = offerPrice;
        this.timeForWork = timeForWork;
        this.describe = describe;
        this.customers = new Customer();
        this.customers.setId(customers);
    }
}
