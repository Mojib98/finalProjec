package com.finalProject.Project.repository.interfaces;

import com.finalProject.Project.entity.Expert;
import com.finalProject.Project.entity.User;
import com.finalProject.Project.entity.enumeration.UserStatus;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SingUpRepository<T extends User> extends JpaRepository<T,Integer>, JpaSpecificationExecutor<User> {
    @Override
    List<User> findAll(Specification<User> spec);
    @Modifying
    @Query("UPDATE User c " +
            "SET c.status =:status  " +
            "WHERE c.email = :email")
    int updadeStatusUser(@Param(value = "status")UserStatus userStatus,
                          @Param(value = "email") String email);


}
