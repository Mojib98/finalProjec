package org.project.service;

import org.project.entity.Customer;

import java.util.List;

public interface CustomerService extends GenericService<Customer>{
    List<Customer> search(Customer customer);
}
