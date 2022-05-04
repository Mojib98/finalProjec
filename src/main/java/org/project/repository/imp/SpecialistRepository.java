package org.project.repository.imp;

import org.hibernate.SessionFactory;
import org.project.entity.Order;
import org.project.entity.Specialist;

import java.util.List;

public class SpecialistRepository {
    private final SessionFactory sessionFactory =SessionFactorySingleton.getInstance();

    public List<Order> findOrders(Integer id){
        var session = sessionFactory.getCurrentSession();
        String sql="select * from orders e inner join service s on s.id = e.service_id " +
                "inner join specialist_service ss on s.id = ss.services_id where ss.specialists_id=?";
        var query = session.createNativeQuery(sql,"orders").setParameter(1,id);
        return (List<Order>) query.getResultList();

    }
    public Specialist findByEmail(String email){
        var session = sessionFactory.getCurrentSession();
        var criteriaBuilder = session.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(Specialist.class);
        var root = criteriaQuery.from(Specialist.class);
        criteriaQuery.select( root ).
                where( criteriaBuilder.equal( root.get("email"),email));
        return session.createQuery(criteriaQuery).uniqueResult();
    }
}
