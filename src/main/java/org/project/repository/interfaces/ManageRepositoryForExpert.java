package org.project.repository.interfaces;

import org.project.entity.Expert;
import org.project.entity.RequestForNewSpecialization;
import org.project.entity.Specialty;

import java.util.List;

public interface ManageRepositoryForExpert {
    void changeStatusExpert(Expert expert);
    List<Expert> search(Expert expert);
    List<Expert> RequestList();
}
