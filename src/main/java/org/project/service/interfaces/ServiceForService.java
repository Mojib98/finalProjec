package org.project.service.interfaces;

import org.project.entity.Customer;
import org.project.entity.Service;
import org.project.entity.SubService;

import java.util.List;

public interface ServiceForService extends GenericService<Service>{
     void addService(Service service);
     void addSubService(SubService service);
     List<SubService> findByCategory(String categoryName);
     List<SubService> showAllSubService();
}
