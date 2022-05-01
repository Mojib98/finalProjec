package org.project.repository;

import org.project.entity.BaseClass;
import org.project.entity.Customer;

import java.util.List;

public interface CustomerRepository<T extends BaseClass> {
    List<Customer> search(T t);
    
}
