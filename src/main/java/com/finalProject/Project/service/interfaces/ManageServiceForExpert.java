package com.finalProject.Project.service.interfaces;

import com.finalProject.Project.entity.BaseClass;
import com.finalProject.Project.entity.Expert;
import com.finalProject.Project.entity.Specialty;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface ManageServiceForExpert{
    void changeStatusExpert(Expert expert);
    void handleRequestForExpert(List<Expert> accepted, List<Expert> unAccepted);
    List<Expert> search(Expert expert);
    List<Expert> requestListSingUp();
     void handelRequestForSpecialty(List<Specialty> accepted, List<Specialty> unAccepted);
     void removeSpecialty(Specialty specialty);
    List<Specialty> requestListSpecialty();
    void remove(Integer id);
    void update(Expert expert);
    void insert(Expert expert);
    Expert findById(Integer id);
    Specification<Expert> option(Expert expert);



}
