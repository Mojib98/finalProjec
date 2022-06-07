package com.finalProject.Project.repository.interfaces;

import com.finalProject.Project.entity.*;
import com.finalProject.Project.entity.enumeration.UserStatus;
import com.finalProject.Project.entity.enumeration.WorkStatus;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Integer>, JpaSpecificationExecutor<Customer> {

    List<Customer> findAll(Specification<Customer> spec);

    @Modifying
    @Query("update Customer set password=:password where email=:email and password=:pass")
    void updatePassword(@Param(value = "password") String id,
                        @Param(value = "email") String email,
                        @Param(value = "pass") String pass);
    @Query(
            "select o from Order c inner join c.customers o  " +
                    "group by o having count(c)=:number"
    )
    List<Customer> findAllByNumberOrder(@Param(value = "number") Long number);


}
