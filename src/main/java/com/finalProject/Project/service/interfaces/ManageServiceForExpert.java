package com.finalProject.Project.service.interfaces;

import com.finalProject.Project.entity.BaseClass;
import com.finalProject.Project.entity.Expert;
import com.finalProject.Project.entity.Specialty;

import java.util.List;

public interface ManageServiceForExpert{
    void changeStatusExpert(Expert expert);
    void handleRequestForExpert(List<Expert> accepted, List<Expert> unAccepted);
    List<Expert> search(Expert expert);
    List<Expert> requestListSingUp();
    public void handelRequestForSpecialty(List<Specialty> accepted, List<Specialty> unAccepted);
    public void removeSpecialty(Specialty specialty);
    List<Specialty> requestListSpecialty();



}
