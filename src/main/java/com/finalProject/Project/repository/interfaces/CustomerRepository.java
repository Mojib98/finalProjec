package com.finalProject.Project.repository.interfaces;

import com.finalProject.Project.entity.*;
import com.finalProject.Project.entity.enumeration.WorkStatus;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer,Integer>,JpaSpecificationExecutor<Customer>{

    List<Customer> findAll(Specification<Customer> spec);

    
}
