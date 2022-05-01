package org.project.service.imp;

import org.hibernate.SessionFactory;
import org.project.entity.RequestForConfirmation;
import org.project.repository.imp.SessionFactorySingleton;
import org.project.repository.imp.SingUpRepository;

public class SingUpService {
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    private final SingUpRepository sing = new SingUpRepository();

    public Integer requestForSingUp(RequestForConfirmation request){
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                Integer track=request.hashCode();
                request.setTrackingNumber(track);
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
}
