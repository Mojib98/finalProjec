/*
package org.project.service.imp;

import org.hibernate.SessionFactory;
import org.project.entity.*;
import org.project.entity.enumeration.WorkStatus;
import org.project.repository.imp.CustomerRepository;
import org.project.repository.imp.SessionFactorySingleton;
import org.project.service.interfaces.CustomerService;

import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImplImpl extends GenericServiceImpl<Order> implements CustomerService {
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    private final CustomerRepository customerRepository = new CustomerRepository();
    public List<Offer> AllOffer(Integer id) {
        List<Offer> list = null;
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                list = customerRepository.findAllOffer(25);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
                return null;
            }
            return list;
        }
    }

    public Offer findOfferById(Integer id) {
        Offer offer = null;
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                offer = customerRepository.findOfferById(id);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
                return null;
            }
            return offer;
        }
    }

    @Override
    public void choice(Offer offer, Order order) {

    }



    public Order findOrderById(Integer id) {
        Order order = null;
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                order = customerRepository.findOrder(id);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
                return null;
            }
            return order;
        }
    }

    public List<Order> findDownOrder(Integer id) {
        List<Order> list = null;
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                list = customerRepository.findDownOrder(25);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
                return null;
            }
            return list;
        }
    }

    public Offer myAcceptOffer(Integer id) {
        Offer acceptOffer = null;
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
            return acceptOffer;
        }
    }

    @Override
    public void paying(Offer offer, Integer price) {

    }

  */
/*  public void paying(AcceptOffer acceptOffer,Integer id) {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                customerRepository.changeWorkBySpecialist(acceptOffer.getId(),WorkStatus.PAYED);
                Order order = customerRepository.findOrder(acceptOffer.getOrder().getId());
                Expert specialist = customerRepository.find(110);
                System.out.println(specialist.getBudget());
                Customer customer = customerRepository.findCustomer(id);
                System.out.println(customer.getBudget());
                int a=customer.getBudget().getBudget().compareTo(acceptOffer.getOfferPrice());
//                if (a<0){

//                }else {
                    Double c = customer.getBudget().getBudget();
                    Double price = acceptOffer.getOfferPrice();
                    customer.getBudget().setBudget(c-price); ;
                   Double s= specialist.getBudget().getBudget();
                   specialist.getBudget().setBudget(s+price);
                   customerRepository.payIng(customer,specialist);

                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
            }
        }
    }*//*

    public void addComment(AcceptOffer acceptOffer , Integer id,Comment comment){
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                Customer customer = customerRepository.findCustomer(id);
                AcceptOffer acceptOffer1 = customerRepository.findAcceptOrder(acceptOffer.getId());
                comment.setCustomer(customer);
                customerRepository.insertComment(comment);
                acceptOffer1.setComment(comment);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
            }
        }
    }
    public void changePassword(Customer customer,String newPassword){
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                 customerRepository.changePassword(customer,newPassword);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
            }
        }
    }

}
*/
