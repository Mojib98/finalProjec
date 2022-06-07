package com.finalProject.Project.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.finalProject.Project.entity.enumeration.WorkStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OfferDto {
    private Integer id;
    private Integer offerPrice;
    private Integer timeForWork;
    //    private LocalTime time;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime workTime;
    private String localDateTime;
    private WorkStatus workStatus;
    private Integer commentId;
    private String expertName;
    private Integer orderId;
    private Integer subServiceId;
    private String subServiceName;

}

