package org.project.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
@Data
public class AcceptOffer extends BaseClass {
    private Double offerPrice;
    private LocalDateTime workTime;
    private Integer timeWorkPerMinute;
    @OneToOne(mappedBy = "acceptOffer")
    private Order order;
    @ManyToOne()
    private Specialist specialists;
}
