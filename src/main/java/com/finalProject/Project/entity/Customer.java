package com.finalProject.Project.entity;

import com.finalProject.Project.entity.enumeration.UserStatus;
import lombok.*;


import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@Data
@NoArgsConstructor
public class Customer extends  User{
    @OneToMany(mappedBy = "customers")
    private List<Order> orders;
    private Integer budget;


    public Customer(Integer id, LocalDateTime time, String firstName, String lastName, String email, String password, UserStatus status) {
        super(id, time, firstName, lastName, email, password, status);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "orders=" + orders +
                ", budget=" + budget +
                "} " + super.toString();
    }
}
