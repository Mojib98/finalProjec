package org.project.service.imp;

import org.hibernate.SessionFactory;
import org.project.entity.Customer;
import org.project.entity.RequestForConfirmation;
import org.project.entity.enumeration.UserStatus;
import org.project.repository.imp.SessionFactorySingleton;
import org.project.repository.imp.SingUpRepository;

public class SingUpService implements org.project.service.interfaces.SingUpService {
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    private final SingUpRepository sing = new SingUpRepository();

    public Integer requestForSingUp(RequestForConfirmation request){
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                Integer track=Math.abs(request.hashCode());
                request.setTrackingNumber(track);
                request.setStatus(UserStatus.AWAITING_CONFIRMATION);
                sing.requestForSpecialist(request);
                transaction.commit();
                return track;
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
                return null;

            }

        }
    }
    public RequestForConfirmation tracking(Integer trackNumber) {
        RequestForConfirmation request = null;

        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                request = sing.findByTrackingNumber(trackNumber);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
//                System.out.println(e.getMessage());
                e.printStackTrace();
                return null;

            }
        }
        return request;
    }
    public void removeRequest(RequestForConfirmation request){
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                sing.removeRequest(request);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
//                System.out.println(e.getMessage());
                e.printStackTrace();

            }
        }
    }
    public void insertCustomer(Customer customer){
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                sing.insertCustomer(customer);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
//                System.out.println(e.getMessage());
                e.printStackTrace();

            }
        }
    }
}
