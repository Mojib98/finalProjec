package com.finalProject.Project.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.finalProject.Project.entity.Comment;
import com.finalProject.Project.entity.Expert;
import com.finalProject.Project.entity.Order;
import com.finalProject.Project.entity.enumeration.WorkStatus;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
@Data
public class OfferDto {
    private Integer offerPrice;
    private Integer timeForWork;
    //    private LocalTime time;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime workTime;
    @JsonIgnore
    private WorkStatus workStatus;
    private Integer commentId;
    private String expertName;
    private Integer orderId;
}
