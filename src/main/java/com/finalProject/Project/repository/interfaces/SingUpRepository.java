package com.finalProject.Project.repository.interfaces;

import com.finalProject.Project.entity.Users;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface SingUpRepository<T extends Users> extends JpaRepository<T,Integer>, JpaSpecificationExecutor<Users> {
    @Override
    List<Users> findAll(Specification<Users> spec);
}
