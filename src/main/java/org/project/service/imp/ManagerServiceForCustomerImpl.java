package org.project.service.imp;

import org.hibernate.SessionFactory;
import org.project.entity.Customer;
import org.project.entity.enumeration.UserStatus;
import org.project.repository.imp.SessionFactorySingleton;
import org.project.service.interfaces.ManageServiceForCustomer;

import java.util.ArrayList;
import java.util.List;

public class ManagerServiceForCustomerImpl extends GenericServiceImpl<Customer> implements ManageServiceForCustomer {
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    public void changeStatus(Customer customer) {
        if (customer.getStatus().equals(UserStatus.ACTIVE))
            customer.setStatus(UserStatus.INACTIVE);
        else
            customer.setStatus(UserStatus.ACTIVE);
        try (var session = sessionFactory.getCurrentSession()){
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                update(customer);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());

            }
        }
    }

    @Override
    public List<Customer> search(Customer customer) {
        List<Customer> list = new ArrayList<>();
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
                return null;
            }
            return list;
        }
    }


}
