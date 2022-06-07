package com.finalProject.Project.entity;

import com.finalProject.Project.entity.enumeration.WorkStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
@NamedNativeQueries({@NamedNativeQuery(
        name = "Order.findOrderForExpert",
        query =
                "select * from orders o inner join sub_service ss on ss.id = o.sub_service_id " +
                        "    inner join service s on s.id = ss.service_id" +
                        "    inner join specialty s2 on s.id = s2.service_id where s2.expert_id=?  and( o.work_status='WAIT_FOR_ARRIVE' or  o.work_status='WAIT_FOR_CHOICE')", resultClass = Order.class),
        @NamedNativeQuery(
        name = "Order.findOrderForExpertStart",
        query =
                "select * from orders o inner join offer ss on ss.orders_id = o.id " +
                        "   where ss.expert_id=? and o.work_status='WAIT_FOR_ARRIVE'", resultClass = Order.class),
        @NamedNativeQuery(
                name = "Order.findOrderForExpertDown",
                query =
                        "select * from orders o inner join offer ss on ss.orders_id = o.id " +
                                "   where ss.expert_id=? and o.work_status='START'", resultClass = Order.class)
        ,
        @NamedNativeQuery(
                name = "Order.findOrderForExpertHistory",
                query =
                        "select * from orders o inner join offer ss on ss.id = o.offer_id " +
                                "   where ss.expert_id=? ", resultClass = Order.class)
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
    private Integer orderPrice;
    private LocalDateTime timeForWork;
    private String address;
    private String describe;
    @Enumerated(EnumType.STRING)
    private WorkStatus workStatus;
    @ManyToOne
    private Customer customers;
    @ManyToOne
    private SubService subService;
    @OneToOne(fetch = FetchType.LAZY)
    private Offer offer;
    @Transient
    private String expert;

    public Order(Integer id, LocalDateTime time, Integer offerPrice, LocalDateTime timeForWork, String address, String describe, Customer customers, SubService subService) {
        super(id, time);
        this.orderPrice = offerPrice;
        this.timeForWork = timeForWork;
        this.address = address;
        this.describe = describe;
        this.customers = customers;
        this.subService = subService;
    }

    public Order(Integer id, LocalDateTime time, Integer offerPrice, LocalDateTime timeForWork, String address, String describe, WorkStatus workStatus, Customer customers, SubService subService, Offer acceptOffer) {
        super(id, time);
        this.orderPrice = offerPrice;
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
        this.orderPrice = offerPrice;
        this.timeForWork = timeForWork;
        this.describe = describe;
    }

    public Order(Integer id, Integer offerPrice, LocalDateTime timeForWork, String describe, String  expert,WorkStatus workStatus,String subServiceName) {
        super(id, null);
        this.subService=new SubService();
        this.orderPrice = offerPrice;
        this.timeForWork = timeForWork;
        this.describe = describe;
        this.expert = expert;
        this.workStatus=workStatus;
        this.subService.setName(subServiceName);
    }
}
