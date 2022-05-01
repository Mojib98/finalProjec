package org.project.repository.imp;

import org.hibernate.SessionFactory;
import org.project.entity.Customer;

import java.util.List;

public class CustomerRepository extends GenericRepositoryImpl<Customer> implements org.project.repository.CustomerRepository {


    private final SessionFactory sessionFactory =SessionFactorySingleton.getInstance();
    public List<Customer> search


}
