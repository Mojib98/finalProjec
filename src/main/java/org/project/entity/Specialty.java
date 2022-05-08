package org.project.entity;

import lombok.Data;
import org.project.entity.enumeration.UserStatus;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Specialty extends BaseClass{
    @ManyToOne
    private Expert expert;
    @ManyToOne
    private Service service;
    @Enumerated(EnumType.STRING)
    private UserStatus status;

}

