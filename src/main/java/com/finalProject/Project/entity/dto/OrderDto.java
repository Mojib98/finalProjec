package com.finalProject.Project.entity.dto;
import com.finalProject.Project.entity.enumeration.WorkStatus;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class OrderDto {
    private Integer offerPrice;
    private LocalDateTime timeForWork;
    private String address;
    private String describe;
    private WorkStatus workStatus;
    private String customersName;
    private String subServiceName;
    private Integer offerId;
}
