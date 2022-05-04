package org.project.service.interfaces;

import org.project.entity.Customer;
import org.project.entity.Order;

import java.util.List;

public interface CustomerService extends GenericService<Order>{
    List<Customer> search(Customer customer);
}
