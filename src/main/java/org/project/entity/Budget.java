package org.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Check(constraints = "budget >= 0")
public class Budget extends BaseClass{
    private Double budget;
}
