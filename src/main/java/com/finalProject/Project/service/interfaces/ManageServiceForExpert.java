package com.finalProject.Project.service.interfaces;

import com.finalProject.Project.entity.BaseClass;
import com.finalProject.Project.entity.Customer;
import com.finalProject.Project.entity.Expert;
import com.finalProject.Project.entity.Specialty;
import com.finalProject.Project.entity.dto.SpecialistDto;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface ManageServiceForExpert{
    void changeStatusExpert(Expert expert);
    void handleRequestForExpert(List<Integer> accepted);
    List<Expert> search(Expert expert);
    List<Expert> requestListSingUp();
     void handelRequestForSpecialty(List<Integer> accepted);
     void removeSpecialty(Specialty specialty);
    List<Specialty> requestListSpecialty();
    void remove(Integer id);
    void update(Expert expert);
    void insert(Expert expert);
    Expert findById(Integer id);
    Specification<Expert> option(Expert expert);
    void insertSpecialty(SpecialistDto specialistDto);
    List<Customer> searchCustomer(Customer customer);



}
