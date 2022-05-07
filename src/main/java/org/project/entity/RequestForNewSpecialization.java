package org.project.entity;

import lombok.*;
import org.project.entity.enumeration.UserStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RequestForNewSpecialization extends BaseClass {
    private String name;
    private String Description;
    @Enumerated(EnumType.STRING)
    private UserStatus statusUser;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Expert specialist;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Service service;

}

