package org.project.repository.interfaces;

import org.project.entity.RequestForConfirmation;
import org.project.entity.RequestForNewSpecialization;
import org.project.entity.Service;
import org.project.entity.Expert;

import java.util.List;
import java.util.Properties;

public interface ManageRepositoryForSpecialist {
    void changeStatus(Properties properties);
    void changeStatus(RequestForConfirmation request);
    void insertSpecial(Expert specialist);
    void unAccept(RequestForNewSpecialization request);
    List<RequestForNewSpecialization> findNewRequest();
    void handleRequestForSpecialization(Service service , Expert specialist);
    List<Expert> search(Expert specialist);
    List<RequestForConfirmation> requestList();
    void removeRequestForNewSpec(RequestForNewSpecialization request);
}
