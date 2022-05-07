package org.project.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AcceptOffer extends BaseClass {
    private Double offerPrice;
    private LocalDateTime workTime;
    private Budget timeWorkPerMinute;
    private Order order;
    private Expert specialists;
    private Comment comment;
}
