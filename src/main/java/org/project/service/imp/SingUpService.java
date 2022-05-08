package org.project.service.imp;

import org.hibernate.SessionFactory;
import org.project.entity.Budget;
import org.project.entity.Customer;
import org.project.entity.Expert;
import org.project.entity.RequestForConfirmation;
import org.project.entity.enumeration.UserStatus;
import org.project.repository.imp.SessionFactorySingleton;
import org.project.repository.imp.SingUpRepository;

public class SingUpService implements org.project.service.interfaces.SingUpService {
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    private final SingUpRepository sing = new SingUpRepository();

    public void requestForSingUp(Expert expert){
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                expert.setStatus(UserStatus.AWAITING_CONFIRMATION);
                sing.requestForSpecialist(expert);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());

            }

        }
    }
    public void insertCustomer(Customer customer){
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                customer.setStatus(UserStatus.ACTIVE);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
                e.printStackTrace();

            }
        }
    }

}
