package org.project.service.interfaces;

import org.project.entity.BaseClass;
import org.project.entity.Expert;

import java.util.List;

public interface ManageServiceForExpert extends GenericService<BaseClass> {
    void changeStatusExpert(Expert expert);
    void handleRequestForExpert(List<Expert> accepted,List<Expert> unAccepted);
    List<Expert> search(Expert expert);
    List<Expert> requestListSingUp();



}
