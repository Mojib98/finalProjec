package com.finalProject.Project.entity.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.finalProject.Project.entity.Avatar;
import com.finalProject.Project.entity.User;
import com.finalProject.Project.entity.enumeration.UserStatus;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.time.LocalDateTime;
@JsonAutoDetect
@Setter
@Getter
@Data
@NoArgsConstructor
public class UserDto extends User {
//    private Avatar avatar;
   private MultipartFile image;
    private Integer budget;

/*
    public UserDto(@NotNull String firstName, @NotNull String lastName, String email, String password, UserStatus status, Avatar avatar, MultipartFile image, Integer budget) {
        super(firstName, lastName, email, password, status);
//        this.avatar = avatar;
        this.image = image;
        this.budget = budget;
    }
*/

    @Override
    public String toString() {
        return "UserDto{" +
                "avatar=" + image +
                ", image=" + image +
                ", budget=" + budget +
                "} " + super.toString();
    }
}
