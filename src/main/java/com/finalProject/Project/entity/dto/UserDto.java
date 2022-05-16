package com.finalProject.Project.entity.dto;

import com.finalProject.Project.entity.Avatar;
import com.finalProject.Project.entity.User;
import com.finalProject.Project.entity.enumeration.UserStatus;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
@Data
@NoArgsConstructor
public class UserDto extends User {
    private Avatar avatar;
    private Integer budget;
    @Override
    public String toString() {
        return "UserDto{" +
                "avatar=" + avatar +
                ", budget=" + budget +
                "} " + super.toString();
    }
}
