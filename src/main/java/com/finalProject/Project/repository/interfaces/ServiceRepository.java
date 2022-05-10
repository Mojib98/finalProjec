package com.finalProject.Project.repository.interfaces;

import com.finalProject.Project.entity.Service;
import org.springframework.data.repository.CrudRepository;

public interface ServiceRepository extends CrudRepository<Service,Integer> {
    Service findByName(String name);
}
