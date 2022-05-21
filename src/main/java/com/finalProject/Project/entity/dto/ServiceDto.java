package com.finalProject.Project.entity.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class ServiceDto {
    private Integer id;
    private String serviceName;
    private String subServiceName;
    private Integer basePrice;
    private String describe;

}
