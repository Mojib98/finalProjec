/*
package org.project.service.imp;

import org.hibernate.SessionFactory;
import org.project.entity.*;
import org.project.entity.enumeration.WorkStatus;
import org.project.repository.imp.SessionFactorySingleton;
import org.project.repository.imp.SpecialistRepository;

import java.util.List;

public class SpecialistService extends GenericServiceImpl<BaseClass> implements org.project.service.interfaces.SpecialistService {
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    SpecialistRepository specialistRepository = new SpecialistRepository();
    public List<Order> findOrders(Expert specialist){
        List<Order> orders = null;
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                System.out.println(specialist.getId());
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
    public Expert findByEmail(String email){
        Expert specialist = null;
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
    public List<AcceptOffer> findMyAcceptOffer(Integer id){
        List<AcceptOffer> list = null;
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                list = specialistRepository.findMyAcceptOffer(id);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
//                System.out.println(e.getMessage());
                e.printStackTrace();

            }
        }
        return list;
    }
    public void changeWorkBySpecialist(Integer id, WorkStatus workStatus){
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                specialistRepository.changeWorkBySpecialist(id,workStatus);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
//                System.out.println(e.getMessage());
                e.printStackTrace();

            }
        }
    }
    public void changePassword(Expert specialist, String newPassword){
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                specialistRepository.changePassword(specialist,newPassword);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
            }
        }
    }

}
*/
