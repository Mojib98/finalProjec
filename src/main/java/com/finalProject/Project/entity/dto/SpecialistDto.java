package com.finalProject.Project.entity.dto;

import com.finalProject.Project.entity.enumeration.UserStatus;
import lombok.Data;

@Data
public class SpecialistDto {
    private String serviceName;
    private String expertFirstName;
   private String expertLastName;
    private Integer serviceId,expertId,id;
    private UserStatus userStatus;
}
