package org.project.service.imp;

import org.hibernate.SessionFactory;
import org.project.entity.Customer;
import org.project.entity.Expert;
import org.project.entity.Users;
import org.project.entity.enumeration.UserStatus;
import org.project.repository.imp.SessionFactorySingleton;
import org.project.repository.imp.SingUpRepository;

public class SingUpService extends GenericServiceImpl<Users> implements org.project.service.interfaces.SingUpService {
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    private final SingUpRepository sing = new SingUpRepository();

    public void requestForSingUp(Expert expert){

                expert.setStatus(UserStatus.AWAITING_CONFIRMATION);
                expert.setBudget(0);
                insert(expert);

    }
    public void insertCustomer(Customer customer){
                customer.setStatus(UserStatus.ACTIVE);
                customer.setBudget(0);
                insert(customer);

    }

}
