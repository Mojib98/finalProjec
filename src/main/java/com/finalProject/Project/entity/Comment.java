package com.finalProject.Project.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Setter
@Getter
@Data
public class Comment extends BaseClass{
    private String Comment;
    @OneToOne
    private Customer customer;
}
