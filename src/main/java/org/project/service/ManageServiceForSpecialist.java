package org.project.service;

import org.project.entity.RequestForConfirmation;
import org.project.entity.RequestForNewSpecialization;
import org.project.entity.Specialist;

import java.util.List;
import java.util.Properties;

public interface ManageServiceForSpecialist extends GenericService<Specialist> {
    void changeStatus(Properties properties);
    void handleRequest(List<RequestForConfirmation> request);
    void deleteFromRequestList(List<RequestForConfirmation> request);
    void acceptSpecial(List<RequestForNewSpecialization> request);
    List<RequestForNewSpecialization> findNewRequest();
    void handleRequestForSpecialization(List<RequestForNewSpecialization> request);
    List<Properties> search(Properties properties);
    List<RequestForConfirmation> RequestList();

}
