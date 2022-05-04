package org.project.repository.imp;

import org.hibernate.SessionFactory;
import org.project.entity.Customer;
import org.project.entity.RequestForConfirmation;
import org.project.entity.RequestForNewSpecialization;

public class SingUpRepository {
    private final SessionFactory sessionFactory =SessionFactorySingleton.getInstance();
    public void requestForSpecialist(RequestForConfirmation request){
        var session = sessionFactory.getCurrentSession();
        session.save(request);
    }
    public String findByTrackingNumber(Integer track){
        var session = sessionFactory.getCurrentSession();
        var criteriaBuilder = session.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(Enum.class);
        var root = criteriaQuery.from(RequestForConfirmation.class);
        criteriaQuery.select( root.get("status") ).
      where( criteriaBuilder.equal( root.get("trackingNumber"),track));
        var status= session.createQuery(criteriaQuery).uniqueResult();
        return status.name();
    }
    public void removeRequest(RequestForConfirmation request){
        var session = sessionFactory.getCurrentSession();
        var criteriaBuilder = session.getCriteriaBuilder();
        var delete = criteriaBuilder.createCriteriaDelete(RequestForConfirmation.class);
        var root = delete.from(RequestForConfirmation.class);
        delete.where(criteriaBuilder.equal(root.get("id"), request.getId()));

        // perform update
        session.createQuery(delete).executeUpdate();
    }
    public void insertCustomer(Customer customer){
        var session = sessionFactory.getCurrentSession();
        session.save(customer);
    }
}
