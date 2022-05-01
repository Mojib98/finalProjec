package org.project.service.imp;

import org.hibernate.SessionFactory;
import org.project.entity.Customer;
import org.project.repository.CustomerRepository;
import org.project.repository.imp.SessionFactorySingleton;
import org.project.service.CustomerService;

import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImplImpl extends GenericServiceImpl<Customer> implements CustomerService {
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    private final CustomerRepository<Customer> customerRepository = new org.project.repository.imp.CustomerRepository();
    public List<Customer> search(Customer customer) {
        List<Customer> list = new ArrayList<>();
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                list = customerRepository.search(customer);
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
