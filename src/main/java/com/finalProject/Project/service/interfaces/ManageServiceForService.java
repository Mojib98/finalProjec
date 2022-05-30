package com.finalProject.Project.service.interfaces;
import com.finalProject.Project.entity.Expert;
import com.finalProject.Project.entity.Service;
import com.finalProject.Project.entity.SubService;

import java.util.List;

public interface ManageServiceForService {

    List<Service> showAllService();
    void insertService(String service);
    void insertSubService(SubService service);
   Service findServiceByName(String name);
    List<SubService> showAllSubService();

}
