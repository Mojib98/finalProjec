package org.project.repository.imp;

import org.hibernate.SessionFactory;
import org.project.entity.BaseClass;
import org.project.entity.Customer;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;


public class CustomerRepository extends GenericRepositoryImpl<Customer> implements org.project.repository.CustomerRepository<Customer> {


    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();



}
