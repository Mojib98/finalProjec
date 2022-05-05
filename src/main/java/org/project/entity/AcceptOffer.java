package org.project.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AcceptOffer extends BaseClass {
    private Double offerPrice;
    private LocalDateTime workTime;
    private Integer timeWorkPerMinute;
    @OneToOne(mappedBy = "acceptOffer",fetch = FetchType.EAGER)
    private Order order;
    @ManyToOne(fetch = FetchType.EAGER)
    private Specialist specialists;
    @OneToOne(fetch = FetchType.EAGER)
    private Comment comment;

    public AcceptOffer(Integer id, LocalDateTime time) {
        super(id, time);
    }

    public AcceptOffer(Integer id, LocalDateTime time, Double offerPrice, LocalDateTime workTime, Integer timeWorkPerMinute, Order order, Specialist specialists, Comment comment) {
        super(id, time);
        this.offerPrice = offerPrice;
        this.workTime = workTime;
        this.timeWorkPerMinute = timeWorkPerMinute;
        this.order = order;
        this.specialists = specialists;
        this.comment = comment;
    }

    public AcceptOffer(Integer id, LocalDateTime time, LocalDateTime workTime) {
        super(id, time);
        this.workTime = workTime;
    }
}
