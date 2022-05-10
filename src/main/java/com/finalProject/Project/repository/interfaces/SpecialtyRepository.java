package com.finalProject.Project.repository.interfaces;

import com.finalProject.Project.entity.Customer;
import com.finalProject.Project.entity.Specialty;
import com.finalProject.Project.entity.enumeration.UserStatus;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SpecialtyRepository extends CrudRepository<Specialty,Integer> {
    List<Specialty> findAllByStatus(UserStatus status);
}
