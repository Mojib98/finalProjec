package org.project.repository.imp;

import org.hibernate.SessionFactory;
import org.project.entity.Orders;


public class CustomerRepository extends GenericRepositoryImpl<Orders> implements org.project.repository.interfaces.CustomerRepository<Orders> {


    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();



}
