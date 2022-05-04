package org.project.service.imp;

import org.hibernate.SessionFactory;
import org.project.entity.BaseClass;
import org.project.entity.Orders;
import org.project.repository.imp.SessionFactorySingleton;
import org.project.repository.imp.SpecialistRepository;

import java.util.List;

public class SpecialistService extends GenericServiceImpl<BaseClass>{
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    SpecialistRepository specialistRepository = new SpecialistRepository();
    public Orders findOrders(){
        Orders orders = null;
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                orders = specialistRepository.findOrders(16);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
//                System.out.println(e.getMessage());
                e.printStackTrace();

            }
        }
        return orders;
    }
}
