package org.project.repository.imp;

import org.hibernate.SessionFactory;
import org.project.entity.Budget;
import org.project.entity.Customer;
import org.project.entity.Expert;
import org.project.entity.RequestForConfirmation;
import org.project.entity.enumeration.UserStatus;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import java.util.List;

public class SingUpRepository implements org.project.repository.interfaces.SingUpRepository {
    private final SessionFactory sessionFactory =SessionFactorySingleton.getInstance();
    public void requestForSpecialist(Expert expert){
        var session = sessionFactory.getCurrentSession();
        session.save(expert);
    }
    public void insertCustomer(Customer customer){
        var session = sessionFactory.getCurrentSession();
        session.save(customer);
    }
}
