package com.finalProject.Project.service.interfaces;
import com.finalProject.Project.entity.Expert;
import com.finalProject.Project.entity.Service;
import com.finalProject.Project.entity.SubService;

import java.util.List;

public interface ManageServiceForService {

    List<Service> showAllService();
    void insertService(Service service);
    void insertSubService(SubService service);
    Boolean isServiceExists(Service service);
    Boolean isSubServiceExists(SubService service);
    List<Expert> search(Expert specialist);
    public Service findServiceByName(String name);
}
