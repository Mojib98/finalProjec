package org.project.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Comment extends BaseClass{
    private String Comment;
    @OneToOne
    private Customer customer;
}
