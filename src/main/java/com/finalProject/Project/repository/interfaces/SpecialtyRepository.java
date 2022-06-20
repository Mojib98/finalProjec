package com.finalProject.Project.repository.interfaces;

import com.finalProject.Project.entity.Customer;
import com.finalProject.Project.entity.Specialty;
import com.finalProject.Project.entity.enumeration.UserStatus;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpecialtyRepository extends CrudRepository<Specialty,Integer> {
    @Query("select new Specialty(s.id,s.expert.firstName,s.expert.lastName,s.service.name) from Specialty s where s.status=:status")
    List<Specialty> find( @Param("status") UserStatus status);
}
