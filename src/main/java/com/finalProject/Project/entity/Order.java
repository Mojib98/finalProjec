package com.finalProject.Project.entity;

import com.finalProject.Project.entity.enumeration.WorkStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/*@SqlResultSetMapping(
        name = "orders",
        classes = @ConstructorResult(
                targetClass = Order.class,
                columns = {
                        @ColumnResult(name = "id",type = Budget.class),
                        @ColumnResult(name = "time",type = LocalDateTime.class),
                        @ColumnResult(name = "offerprice",type = Double.class),
                        @ColumnResult(name = "timeforwork",type = LocalDateTime.class),
                        @ColumnResult(name = "describe",type = String.class),
                        @ColumnResult(name = "customers_id",type = Customer.class)

                })

)*/
@NamedNativeQueries({
       /* @NamedNativeQuery(
                name = "Person.findAllPersons",
                query =
                        "SELECT * " +
                                "FROM Person ", resultClass = Person.class
        ),*/
        @NamedNativeQuery(
                name = "Order.findOrderForExpert",
                query =
                        "select * from orders o inner join sub_service ss on ss.id = o.sub_service_id " +
                                "    inner join service s on s.id = ss.category_id" +
                                "    inner join specialty s2 on s.id = s2.service_id where s2.expert_id=?", resultClass = Order.class)

})
@SqlResultSetMapping(
        name = "orders",
        classes = @ConstructorResult(
                targetClass = Order.class,
                columns = {
                        @ColumnResult(name = "id",type = Integer.class),
                        @ColumnResult(name = "time",type = LocalDateTime.class),
                        @ColumnResult(name = "offerprice",type = Integer.class),
                        @ColumnResult(name = "timeforwork",type = LocalDateTime.class),
                        @ColumnResult(name = "describe",type = String.class),

                })

)
@Entity
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Table(name = "orders")
public class Order extends BaseClass {
    private Integer offerPrice;
    private LocalDateTime timeForWork;
    private String address;
    private String describe;
    @Enumerated(EnumType.STRING)
    private WorkStatus workStatus;
    @ManyToOne
    private Customer customers;
   /* @OneToMany(mappedBy = "order")
    private List<Offer> offers;*/
    @ManyToOne
    private SubService subService;
    @OneToOne(fetch = FetchType.LAZY)
    private Offer offer;

    public Order(Integer id, LocalDateTime time, Integer offerPrice, LocalDateTime timeForWork, String address, String describe, Customer customers, SubService subService) {
        super(id, time);
        this.offerPrice = offerPrice;
        this.timeForWork = timeForWork;
        this.address = address;
        this.describe = describe;
        this.customers = customers;
        this.subService = subService;
    }

    public Order(Integer id, LocalDateTime time, Integer offerPrice, LocalDateTime timeForWork, String address, String describe, WorkStatus workStatus, Customer customers, SubService subService, Offer acceptOffer) {
        super(id, time);
        this.offerPrice = offerPrice;
        this.timeForWork = timeForWork;
        this.address = address;
        this.describe = describe;
        this.workStatus = workStatus;
        this.customers = customers;
        this.subService = subService;
        this.offer = acceptOffer;
    }

    public Order(Integer id, LocalDateTime time, Integer offerPrice, LocalDateTime timeForWork, String describe) {
        super(id, time);
        this.offerPrice = offerPrice;
        this.timeForWork = timeForWork;
        this.describe = describe;
    }
}
