package org.project.service.imp;

import org.hibernate.SessionFactory;
import org.project.entity.BaseClass;
import org.project.entity.Customer;
import org.project.entity.Order;
import org.project.entity.Specialist;
import org.project.repository.imp.SessionFactorySingleton;
import org.project.repository.imp.SpecialistRepository;

import java.util.List;

public class SpecialistService extends GenericServiceImpl<BaseClass>{
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    SpecialistRepository specialistRepository = new SpecialistRepository();
    public List<Order> findOrders(Specialist specialist){
        List<Order> orders = null;
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                orders = specialistRepository.findOrders(specialist.getId());
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
//                System.out.println(e.getMessage());
                e.printStackTrace();

            }
        }
        return orders;
    }
    public Specialist findByEmail(String email){
        Specialist specialist = null;
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                specialist = specialistRepository.findByEmail(email);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
//                System.out.println(e.getMessage());
                e.printStackTrace();

            }
        }
        return specialist;
    }
    public Customer findByIdCustomer(Integer id) {
        Customer customer = null;
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                customer = specialistRepository.findByIdCustomer(id);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
//                System.out.println(e.getMessage());
                e.printStackTrace();

            }
            return customer;
        }
    }
    public void changeWorkFlow(Order order){
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                 specialistRepository.changeWorkFlow( order);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
//                System.out.println(e.getMessage());
                e.printStackTrace();

            }
        }
    }

}
