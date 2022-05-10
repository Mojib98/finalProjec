package com.finalProject.Project.repository.interfaces;

import com.finalProject.Project.entity.SubService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubServiceRepository extends CrudRepository<SubService,Integer> {
    SubService findByName(String name);
}
