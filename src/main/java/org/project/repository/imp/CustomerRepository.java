package org.project.repository.imp;

import org.hibernate.SessionFactory;
import org.project.entity.Offer;
import org.project.entity.Order;

import java.util.List;


public class CustomerRepository extends GenericRepositoryImpl<Order> implements org.project.repository.interfaces.CustomerRepository<Order> {


    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();


    public List<Offer> findAllOffer(Integer id){
        var session = sessionFactory.getCurrentSession();
        String sql="select * from orders o inner join offer o2 on o.id = o2.order_id where customers_id=25;";
        var query = session.createNativeQuery(sql,"offer");
        return (List<Offer>) query.getResultList();
    }
}
