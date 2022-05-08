package org.project.service.interfaces;

import org.project.entity.Expert;
import org.project.entity.Specialty;

import java.util.List;

public interface ManageSpecialty extends GenericService<Specialty>  {
    void handleRequestForSpecialization(List<Specialty> accepted, List<Specialty> unAccepted);
    void changeStatusSpecialty(Specialty specialty);
    List<Specialty> findNewRequestSpecialty();



}
