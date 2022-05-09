package org.project.repository.imp;

import org.hibernate.SessionFactory;
import org.project.entity.Customer;
import org.project.entity.Expert;
import org.project.entity.Offer;
import org.project.entity.Orders;
import org.project.entity.enumeration.WorkStatus;
import org.project.repository.interfaces.ExpertRepository;

import java.util.List;

public class ExpertRepositoryImpl implements ExpertRepository {
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();


    @Override
    public List<Orders> findOrders(Expert expert) {
        var session = sessionFactory.getCurrentSession();
        String hql="from Orders o inner join Service s " +
                "inner join Specialty p where p.id=:id and o.workStatus='WAIT_FOR_ARRIVE' or o.workStatus=:work";
        var query = session.createQuery(hql, Orders.class)
                .setParameter("id",expert.getId())
                .setParameter("work",WorkStatus.WAIT_FOR_OFFER);
        return null;
    }

    @Override
    public Expert findByEmail(String email) {
        var session = sessionFactory.getCurrentSession();
        String hql="from Expert where email=:email";
        var query = session.createQuery(hql, Orders.class)
                .setParameter("email",email) ;
        return null;
    }

    @Override
    public Customer findByIdCustomer(Integer id) {
        var session = sessionFactory.getCurrentSession();
        session.find(Customer.class,id);
        return null;
    }

    @Override
    public void changeWorkFlow(Orders order) {

        var session = sessionFactory.getCurrentSession();
        String hql="update Orders set workStatus=:work where id=:id";
        var query = session.createQuery(hql, Orders.class)
                .setParameter("work",order.getWorkStatus())
                .setParameter("id",order.getId())
                .executeUpdate();
    }

    @Override
    public List<Offer> findMyAcceptOffer(Expert expert) {
        var session = sessionFactory.getCurrentSession();
        String hql="from Orders o inner join Offer f where " +
                "f.expert.id=:id and o.workStatus=:work";
        var query = session.createQuery(hql, Orders.class)
                .setParameter("id",expert.getId())
                .setParameter("work",WorkStatus.WAIT_FOR_ARRIVE);
        return null;
    }

    @Override
    public void changeWorkByExpert(Integer id, WorkStatus workStatus) {
        var session = sessionFactory.getCurrentSession();
        String hql="update Orders set workStatus=:work " +
                "where id=:id";
        var query = session.createQuery(hql, Orders.class)
                .setParameter("id",id)
                .setParameter("work",workStatus)
                .executeUpdate();
    }

    @Override
    public void changePassword(Expert expert, String newPassword) {
        var session = sessionFactory.getCurrentSession();
        String hql = "update Expert  set password=:newPassword " +
                "where email=:email and password=:pass";
        var query=session.createQuery(hql)
                .setParameter("newPassword",newPassword)
                .setParameter("email",expert.getEmail())
                .setParameter("pass",expert.getPassword())
                .executeUpdate();
    }
}
