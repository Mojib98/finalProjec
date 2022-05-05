package org.project.repository.interfaces;

import org.project.entity.Customer;
import org.project.entity.Service;

import java.util.List;

public interface RepositoryService {
    Service findByName(String name);
    List<Service> findByCategory(String name);
    List<Service> findJustCategory();
    List<Service> findJustSpecialty();
    List<Customer> search(Customer customer);
}
