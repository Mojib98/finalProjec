package org.project.service.interfaces;

import org.project.entity.Customer;
import org.project.entity.Orders;

import java.util.List;

public interface CustomerService extends GenericService<Orders>{
    List<Customer> search(Customer customer);
}
