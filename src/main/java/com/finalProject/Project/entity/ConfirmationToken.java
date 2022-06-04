package com.finalProject.Project.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class ConfirmationToken extends BaseClass {
    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime confirmedAt;
    @Column(columnDefinition = "boolean default ture")
    private Boolean isActive;

    @ManyToOne
    private User user;

    public ConfirmationToken( String token, LocalDateTime createdAt, LocalDateTime confirmedAt, User user) {
        super(null , null);
        this.token = token;
        this.createdAt = createdAt;
        this.confirmedAt = confirmedAt;
        this.user = user;
    }

}
