package com.finalProject.Project.entity;

import com.finalProject.Project.entity.enumeration.UserStatus;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
@Table(name = "specialty",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"service_id", "expert_id"})})
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

    public Specialty() {

    }

    public Specialty(Integer id,String firstName,String lastName,String serviceName) {
        super(id, null);
        this.expert = new Expert();
        expert.setLastName(firstName);
        expert.setFirstName(lastName);
        this.service = new Service();
        service.setName(serviceName);
    }
}

