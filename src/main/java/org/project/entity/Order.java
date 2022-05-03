package org.project.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Order extends BaseClass {
    private Double offerPrice;
    private LocalDateTime timeForWork;
    @ManyToOne
    private Customer customers;
    @ManyToOne
    private Service service;
    @OneToMany(mappedBy = "order")
    private List<Offer> offers;

}
