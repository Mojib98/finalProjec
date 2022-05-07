package org.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@SqlResultSetMapping(
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

)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Offer extends BaseClass{
    private Integer offerPrice;
    private Integer timeForWork;
    private LocalDate date;
//    private LocalTime time;
    private LocalDateTime workTime;
    @OneToOne
    private Comment comment;
    @ManyToOne()
    private Expert expert;

}
