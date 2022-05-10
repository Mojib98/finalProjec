package com.finalProject.Project.service.interfaces;

import com.finalProject.Project.entity.BaseClass;
import com.finalProject.Project.entity.Customer;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GenericService<T>{

      T insert(T t);
    T update(T t);
    void remove(T t);
    T findById(Integer id);
    List<T> findAll();
}
