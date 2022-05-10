package com.finalProject.Project.entity;

import com.finalProject.Project.entity.enumeration.WorkStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/*@SqlResultSetMapping(
        name = "offer",
        classes = @ConstructorResult(
                targetClass = Offer.class,
                columns = {
                        @ColumnResult(name = "id",type = Budget.class),
                        @ColumnResult(name = "time",type = LocalDateTime.class),
                        @ColumnResult(name = "offerprice",type = Double.class),
                        @ColumnResult(name = "worktime",type = LocalDateTime.class),
                        @ColumnResult(name = "order_id",type = Order.class),
                        @ColumnResult(name = "specialists_id",type = Expert.class)

                })

)*/
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Offer extends BaseClass {
    private Integer offerPrice;
    private Integer timeForWork;
//    private LocalTime time;
    private LocalDateTime workTime;
    @Enumerated(EnumType.STRING)
    private WorkStatus workStatus;
    @OneToOne
    private Comment comment;
    @ManyToOne()
    private Expert expert;
    @ManyToOne
    private Order orders;

    public Offer(Integer id, LocalDateTime time, Integer offerPrice, LocalDateTime workTime, WorkStatus workStatus, Expert expert, Order orders) {
        super(id, time);
        this.offerPrice = offerPrice;
        this.workTime = workTime;
        this.workStatus = workStatus;
        this.expert = expert;
        this.orders = orders;
    }
}
