package org.project.repository.imp;

import org.hibernate.SessionFactory;
import org.project.entity.Offer;
import org.project.entity.Order;

import java.util.List;


public class CustomerRepository extends GenericRepositoryImpl<Order> implements org.project.repository.interfaces.CustomerRepository<Order> {


    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();


    public List<Offer> findAllOffer(Integer id){
        return null;
    }
}
