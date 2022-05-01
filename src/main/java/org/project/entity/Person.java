package org.project.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.project.entity.enumeration.statuses;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public  abstract class Person extends BaseClass{
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @Column(nullable = false,unique = true)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private statuses status;


}
