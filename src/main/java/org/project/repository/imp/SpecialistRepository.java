package org.project.repository.imp;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.project.entity.Orders;

import java.util.List;

public class SpecialistRepository {
    private final SessionFactory sessionFactory =SessionFactorySingleton.getInstance();

    public List<Orders> findOrders(Integer id){
        var session = sessionFactory.getCurrentSession();
        String sql="select * from orders e inner join service s on s.id = e.service_id " +
                "inner join specialist_service ss on s.id = ss.services_id where ss.specialists_id=?";
        var query = session.createNativeQuery(sql,"orders").setParameter(1,16);
        return (List<Orders>) query.getResultList();

    }
}
