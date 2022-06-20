package com.finalProject.Project.entity.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.finalProject.Project.entity.enumeration.WorkStatus;
import lombok.Data;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class OrderDto {
    private Integer id;
    private Integer offerPrice;
    private Integer orderPrice;
    private LocalDateTime timeForWork;
    private String localDateTime;
    private String address;
    private String describe;
    private WorkStatus workStatus;
    private String customersName;
    private String email;
    private String subServiceName;
    private String serviceName;
    private String expertName;
    private Integer offerId;
    private Integer subServiceId;
    private Float rate;
    private String commentText;
    private Integer  customerId;
    private Integer amount;
    private String startDate;
    private String finishDate;


}
