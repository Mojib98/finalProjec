package com.finalProject.Project.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class ConfirmationToken extends BaseClass {
    @Column(nullable = false)
    private String tokenCode;
    private LocalDateTime confirmedAt;
//    @Column(columnDefinition = "boolean default ture")
    @Column(columnDefinition = "boolean default true")
//    @Column(columnDefinition = "boolean default true")
    private Boolean isActive=true;

    @ManyToOne
//    @Transient
    private User user;

    public ConfirmationToken( String token,  User user) {
        super(null,null);
        this.tokenCode = token;
        this.user = user;
    }



}
