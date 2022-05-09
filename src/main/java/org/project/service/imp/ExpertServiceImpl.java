package org.project.service.imp;

import org.hibernate.SessionFactory;
import org.project.entity.*;
import org.project.entity.enumeration.UserStatus;
import org.project.entity.enumeration.WorkStatus;
import org.project.repository.imp.ExpertRepositoryImpl;
import org.project.repository.imp.SessionFactorySingleton;
import org.project.service.interfaces.ExpertService;

import java.util.List;

public class ExpertServiceImpl extends GenericServiceImpl<BaseClass>  implements ExpertService {
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    private final ExpertRepositoryImpl repository=new ExpertRepositoryImpl();

    @Override
    public List<Orders> findOrders(Expert expert) {
        List<Orders> list = null;
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                list=repository.findOrders(expert);
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
        Expert expert = null;
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                expert=findByEmail(email);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
                return null;
            }
            return expert;
        }
    }

    @Override
    public Customer findByIdCustomer(Integer id) {
        Customer customer=null;
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                customer = repository.findByIdCustomer(id);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
            }
            return customer;
        }
    }

    @Override
    public void changeWorkFlow(Orders order) {
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
        List<Offer> list=null;
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                list=repository.findMyAcceptOffer(expert);
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
                repository.changeWorkByExpert(id,workStatus);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
            }

        }
    }

    @Override
    public void changePassword(Expert expert, String newPassword) {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                repository.changePassword(expert,newPassword);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());

            }
        }
    }
    public void requestForSpecialty(Specialty specialty){
        specialty.setStatus(UserStatus.AWAITING_CONFIRMATION);
                insert(specialty);
    }
}
