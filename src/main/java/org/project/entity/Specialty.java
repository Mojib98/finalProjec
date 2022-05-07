package org.project.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Specialty extends BaseClass{
    @ManyToOne
    private Expert expert;
    @ManyToOne
    private Service service;

}
