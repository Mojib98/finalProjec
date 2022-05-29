package com.finalProject.Project.entity;

import com.finalProject.Project.entity.enumeration.UserStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Expert extends User {
    @Embedded
    private Avatar avatar;
    @Column(columnDefinition = "integer default 0")
    private Integer wallet;
    @Column(columnDefinition = "integer default 5")
    private Float rate;
    public Expert(Integer id, LocalDateTime time, String firstName, String lastName, String email, String password, UserStatus status, Avatar avatar) {
        super(id, time, firstName, lastName, email, password, status);
        this.avatar = avatar;
    }

}
