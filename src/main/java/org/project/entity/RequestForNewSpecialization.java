package org.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.project.entity.enumeration.Statuses;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestForNewSpecialization extends BaseClass {
    private String name;
    private String Description;
    @Enumerated(EnumType.STRING)
    private Statuses statuses;
    @OneToOne
    private Specialist specialist;
}

