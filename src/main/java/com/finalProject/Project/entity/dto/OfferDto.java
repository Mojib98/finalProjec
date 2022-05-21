package com.finalProject.Project.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.finalProject.Project.app.Utility;
import com.finalProject.Project.entity.Comment;
import com.finalProject.Project.entity.Expert;
import com.finalProject.Project.entity.Order;
import com.finalProject.Project.entity.enumeration.WorkStatus;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class OfferDto {
    private Integer offerPrice;
    private Integer timeForWork;
    //    private LocalTime time;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime workTime;
    @JsonIgnore
    private WorkStatus workStatus;
    private Integer commentId;
    private String expertName;
    private Integer orderId;
    private Integer subServiceId;

}

