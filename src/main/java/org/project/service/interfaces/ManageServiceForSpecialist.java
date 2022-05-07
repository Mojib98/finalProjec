package org.project.service.interfaces;

import org.project.entity.RequestForConfirmation;
import org.project.entity.RequestForNewSpecialization;
import org.project.entity.Expert;

import java.util.List;

public interface ManageServiceForSpecialist extends GenericService<Expert> {
    void changeStatus(Expert specialist);

    //    void changeStatus(Properties properties);
    void acceptRequest(List<RequestForConfirmation> request);
    void unAccept(List<RequestForNewSpecialization> request);
    void changeStatusForRequest(List<RequestForConfirmation> request);
    List<RequestForNewSpecialization> findNewRequest();
    void handleRequestForSpecialization(List<RequestForNewSpecialization> request);
    List<Expert> search(Expert specialist);
    List<RequestForConfirmation> RequestList();

}
