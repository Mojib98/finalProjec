package org.project.service.interfaces;

import org.project.entity.RequestForConfirmation;
import org.project.entity.RequestForNewSpecialization;
import org.project.entity.Expert;
import org.project.entity.Specialty;

import java.util.List;

public interface ManageServiceForSpecialist extends GenericService<Expert> {
    void changeStatus(Expert specialist);

    //    void changeStatus(Properties properties);
    void acceptRequest(List<Expert> request);
    void unAccept(List<ٍExpert>);
    void changeStatusForRequest(List<Expert> experts);
    List<Specialty> findNewRequest();
    void handleRequestForSpecialization(List<Specialty> request);
    List<Expert> search(Expert expert);
    List<Expert> RequestList();

}
