package org.project.repository.interfaces;

import org.project.entity.RequestForNewSpecialization;
import org.project.entity.Service;
import org.project.entity.Expert;

import java.util.List;

public interface ManageRepositoryForSpecialist {
    void insertSpecial(Expert specialist);
    void unAccept(Expert expert);
    void Accept(Expert expert);
    void handleRequestForSpecialization(Service service , Expert specialist);
    List<Expert> search(Expert specialist);
}
