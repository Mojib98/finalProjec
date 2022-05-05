package org.project.repository.imp;

import org.hibernate.SessionFactory;
import org.project.entity.Customer;
import org.project.entity.RequestForConfirmation;
import org.project.entity.enumeration.UserStatus;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import java.util.List;

public class SingUpRepository implements org.project.repository.interfaces.SingUpRepository {
    private final SessionFactory sessionFactory =SessionFactorySingleton.getInstance();
    public void requestForSpecialist(RequestForConfirmation request){
        var session = sessionFactory.getCurrentSession();
        session.save(request);
    }
    public RequestForConfirmation findByTrackingNumber(Integer track){
        var session = sessionFactory.getCurrentSession();
        var criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery( Object[].class );
//        var criteriaQuery = criteriaBuilder.createQuery(Enum.class);
        var root = criteriaQuery.from(RequestForConfirmation.class);
        Path<Integer> idPath = root.get("id");
        Path<String> emailPath = root.get("email");
        Path<Integer> idTrack = root.get("trackingNumber");
        Path<Integer> enumPath = root.get("status");
        criteriaQuery.select(criteriaBuilder.array( idPath, emailPath,idTrack,enumPath )).
         where( criteriaBuilder.equal( root.get("trackingNumber"),track));
        List<Object[]> valueArray = session.createQuery( criteriaQuery ).getResultList();
//        var status= session.createQuery(criteriaQuery).uniqueResult();
        RequestForConfirmation request = new RequestForConfirmation();

        for ( Object[] values : valueArray ) {
            request.setId((Integer) values[0]);
            request.setEmail((String) values[1]);
            request.setTrackingNumber((Integer) values[2]);
            request.setStatus((UserStatus) values[3]);

        }

        return request;

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
