package org.project.repository.imp;

import org.hibernate.SessionFactory;
import org.project.entity.AcceptOffer;
import org.project.entity.Customer;
import org.project.entity.Order;
import org.project.entity.Specialist;
import org.project.entity.enumeration.WorkStatus;

import java.util.List;

public class SpecialistRepository {
    private final SessionFactory sessionFactory =SessionFactorySingleton.getInstance();

    public List<Order> findOrders(Integer id){
        var session = sessionFactory.getCurrentSession();
        String sql="select * from orders e inner join service s on s.id = e.service_id " +
                "inner join specialist_service ss on s.id = ss.services_id where ss.specialists_id=:id";
        var query = session.createNativeQuery(sql,"orders").setParameter("id",id);
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
    public Customer findByIdCustomer(Integer id){
        var session = sessionFactory.getCurrentSession();
        String hql="from Customer  where id=:id";
        var query=session.createQuery(hql,Customer.class).setParameter("id",id);
       return query.getSingleResult();
    }
    public void changeWorkFlow(Order order){
        var session = sessionFactory.getCurrentSession();
        String hql="update Order set workStatus =:work where id=:id";
        var query=session.createQuery(hql).setParameter("id",order.getId())
               .setParameter("work",order.getWorkStatus());
        query.executeUpdate();
    }
    public List<AcceptOffer> findMyAcceptOffer(Integer id){
        var session = sessionFactory.getCurrentSession();
        String hql = "select new AcceptOffer(id,time,workTime)from AcceptOffer where specialists.id=:id";
        var query = session.createQuery(hql,AcceptOffer.class)
                .setParameter("id",id);
       return query.getResultList();

    }
    public void changeWorkBySpecialist(Integer id,WorkStatus workStatus){
        var session = sessionFactory.getCurrentSession();
        String hql = "update Order set workStatus =:work where acceptOffer.id=:id";
        var query = session.createQuery(hql)
                .setParameter("id",id)
                .setParameter("work",workStatus);
        query.executeUpdate();
    }
    public void changePassword(Specialist specialist,String password){

        var session = sessionFactory.getCurrentSession();
        String hql = "update Specialist set password=:newPassword " +
                " where email=:email and password=:oldPass";
        var query = session.createQuery(hql)
                .setParameter("email", specialist.getEmail())
                .setParameter("oldPass", specialist.getPassword())
                .setParameter("newPassword",password)
                .executeUpdate();
    }
}
