package org.project.service.interfaces;

import org.project.entity.Expert;
import org.project.entity.Specialty;

import java.util.List;

public interface ManageServiceForExpert extends GenericService<Expert> {
    void changeStatusExpert(Expert expert);
    void handleRequestForExpert(List<Expert> accepted,List<Expert> unAccepted);
    List<Expert> search(Expert expert);
    List<Expert> RequestList();



}
