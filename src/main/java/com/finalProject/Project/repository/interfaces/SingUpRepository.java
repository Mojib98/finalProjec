package com.finalProject.Project.repository.interfaces;

import com.finalProject.Project.entity.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface SingUpRepository<T extends User> extends JpaRepository<T,Integer>, JpaSpecificationExecutor<User> {
    @Override
    List<User> findAll(Specification<User> spec);
}
