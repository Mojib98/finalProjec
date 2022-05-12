package com.finalProject.Project.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Data
public class Comment extends BaseClass{
    private String Comment;
    @OneToOne
    private Customer customer;

    public Comment(Integer id, LocalDateTime time, String comment, Customer customer) {
        super(id, time);
        Comment = comment;
        this.customer = customer;
    }
}
