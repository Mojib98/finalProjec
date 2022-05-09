package org.project.entity;

import lombok.Data;
import org.project.entity.enumeration.UserStatus;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Data
public class Specialty extends BaseClass{
    @ManyToOne
    private Expert expert;
    @ManyToOne
    private Service service;
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    public Specialty(Integer id, LocalDateTime time, Expert expert, Service service) {
        super(id, time);
        this.expert = expert;
        this.service = service;
    }
}

