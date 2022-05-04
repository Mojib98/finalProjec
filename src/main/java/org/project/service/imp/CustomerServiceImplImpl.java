package org.project.service.imp;

import org.hibernate.SessionFactory;
import org.project.entity.Customer;
import org.project.entity.Offer;
import org.project.entity.Order;
import org.project.repository.imp.CustomerRepository;
import org.project.repository.imp.SessionFactorySingleton;
import org.project.service.interfaces.CustomerService;

import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImplImpl extends GenericServiceImpl<Order> implements CustomerService {
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    private final CustomerRepository customerRepository = new CustomerRepository();
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
    public List<Offer> AllOffer(Integer id){
        List<Offer> list = null;
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                list=customerRepository.findAllOffer(25);
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
