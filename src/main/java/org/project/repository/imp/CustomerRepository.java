package org.project.repository.imp;

import org.hibernate.SessionFactory;
import org.project.entity.*;
import org.project.entity.enumeration.WorkStatus;

import java.util.List;


public class CustomerRepository extends GenericRepositoryImpl<Orders> implements org.project.repository.interfaces.CustomerRepository<Orders> {


    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();


    public List<Offer> findAllOffer(Integer id) {
        var session = sessionFactory.getCurrentSession();
        String hql="from Offer o inner join Orders r where r.id=:id and o.workStatus=:work";
        var query = session.createQuery(hql, Offer.class)
                .setParameter("id",id)
                .setParameter("work",WorkStatus.WAIT_FOR_CHOICE);
        return (List<Offer>) query.getResultList();
    }

    public Offer findOfferById(Integer id) {
        var session = sessionFactory.getCurrentSession();
        String hql = " from Offer o " +
                " where o.id=:id";
        var query = session.createQuery(hql, Offer.class).setParameter("id", id);
        return query.getSingleResult();
    }

/*    public AcceptOffer insertAcceptOffer(AcceptOffer acceptOffer) {
        var session = sessionFactory.getCurrentSession();
        session.save(acceptOffer);
        return acceptOffer;
    }*/

    public Orders findOrder(Integer id) {
        var session = sessionFactory.getCurrentSession();
        String hql = "from Orders o " +
                " where o.id=:id";
        var query = session.createQuery(hql, Orders.class).setParameter("id", id);
        return query.getSingleResult();
    }

    public Expert find(Integer id) {
        var session = sessionFactory.getCurrentSession();
        String hql = "from Expert o " +
                " where o.id=:id";
        var query = session.createQuery(hql, Expert.class).setParameter("id", id);
        return query.getSingleResult();
    }

    public List<Orders> findDownOrder(Integer id) {
        var session = sessionFactory.getCurrentSession();
        String hql = "from Orders where workStatus=:work AND customers.id=:id";
        var query = session.createQuery(hql, Orders.class).setParameter("id", id)
                .setParameter("work", WorkStatus.DONE);
        return (List<Orders>) query.getResultList();
    }



    @Override
    public Customer findCustomer(Integer id) {
        return null;
    }

    /*    public AcceptOffer findAcceptOrder(Integer id) {
            var session = sessionFactory.getCurrentSession();
            String hql = "from AcceptOffer o " +
                    " where o.id=:id";
            var query = session.createQuery(hql, AcceptOffer.class).setParameter("id", id);
            return query.getSingleResult();
        }*/
//    public Customer findCustomer(Integer id) {
//        var session = sessionFactory.getCurrentSession();
//        String hql = "select new Customer(o.id,o.time,o.firstName,o.lastName,o.email,o.password,o.status,o.budget) from Customer o " +
//                " where o.id=:id";
//        var query = session.createQuery(hql, Customer.class).setParameter("id", id);
//        return query.getSingleResult();
//    }
    public void insertComment(Comment comment){
        var session = sessionFactory.getCurrentSession();
        session.save(comment);

    }
    public void changePassword(Customer customer,String password){

        var session = sessionFactory.getCurrentSession();
        String hql = "update Customer set password=:newPassword " +
                " where email=:email and password=:oldPass";
        var query = session.createQuery(hql)
                .setParameter("email", customer.getEmail())
                .setParameter("oldPass", customer.getPassword())
                .setParameter("newPassword",password)
                .executeUpdate();
    }
    public void payIng(Customer customer, Expert specialist){
        var session = sessionFactory.getCurrentSession();
        session.update(customer);
        session.update(specialist);

    }
    public void changeWorkBySpecialist(Integer id,WorkStatus workStatus){
        var session = sessionFactory.getCurrentSession();
        String hql = "update Orders set workStatus =:work where id=:id";
        var query = session.createQuery(hql)
                .setParameter("id",id)
                .setParameter("work",workStatus);
        query.executeUpdate();
    }
}
