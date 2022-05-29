package com.finalProject.Project.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Service extends BaseClass{
    @Column(nullable = false,unique = true)
    private String name;
    public Service(Integer id, LocalDateTime time, String name) {
        super(id, time);
        this.name = name;
    }
}
