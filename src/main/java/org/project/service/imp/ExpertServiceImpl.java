package org.project.service.imp;

import org.hibernate.SessionFactory;
import org.project.entity.Customer;
import org.project.entity.Expert;
import org.project.entity.Offer;
import org.project.entity.Order;
import org.project.entity.enumeration.WorkStatus;
import org.project.repository.imp.SessionFactorySingleton;
import org.project.service.interfaces.ExpertService;

import java.util.List;

public class ExpertServiceImpl extends GenericServiceImpl<Offer>  implements ExpertService {
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    @Override
    public List<Order> findOrders(Expert expert) {
        List<Order> list = null;
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

    @Override
    public Expert findByEmail(String email) {
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
            return null;
        }
    }

    @Override
    public Customer findByIdCustomer(Integer id) {
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
            return null;
        }
    }

    @Override
    public void changeWorkFlow(Order order) {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());

            }
        }
    }

    @Override
    public List<Offer> findMyAcceptOffer(Expert expert) {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());

            }
            return null;
        }
    }

    @Override
    public void changeWorkByExpert(Integer id, WorkStatus workStatus) {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
            }

        }
    }

    @Override
    public void changePassword(Expert specialist, String newPassword) {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());

            }
        }
    }
}
