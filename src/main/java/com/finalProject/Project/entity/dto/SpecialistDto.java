package com.finalProject.Project.entity.dto;

import com.finalProject.Project.entity.enumeration.UserStatus;
import lombok.Data;

@Data
public class SpecialistDto {
    private String ServiceName,ExpertFirstName,ExpertLastName;
    private Integer ServiceId,ExpertId;
    private UserStatus userStatus;
}
