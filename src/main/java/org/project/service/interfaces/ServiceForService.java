package org.project.service.interfaces;

import org.project.entity.Customer;
import org.project.entity.Service;

import java.util.List;

public interface ServiceForService extends GenericService<Service>{
     void addService(Service service);
     List<Service> findAll();
     Service findByName(String name);
     List<Service> findByCategory(String categoryName);
     List<Service> findJustCategory();
     List<Service> showAllSpecialty();
     void insertSpecialty(Service service);
     List<Customer> search(Customer customer);
}
