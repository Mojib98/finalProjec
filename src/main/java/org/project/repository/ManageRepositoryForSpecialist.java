package org.project.repository;

import org.project.entity.RequestForConfirmation;
import org.project.entity.RequestForNewSpecialization;
import org.project.entity.Specialist;

import java.util.List;
import java.util.Properties;

public interface ManageRepositoryForSpecialist {
    void changeStatus(Properties properties);
    void acceptSpecial(RequestForNewSpecialization request);
    List<RequestForNewSpecialization> findNewRequest();
    void handleRequestForSpecialization(RequestForNewSpecialization request);
    List<Specialist> search(Specialist specialist);
    List<RequestForConfirmation> RequestList();
    void removeRequestForConf(RequestForConfirmation request);
    void removeRequestForNewSpec(RequestForNewSpecialization request);
}
