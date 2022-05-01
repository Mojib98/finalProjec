package org.project.repository.imp;

import org.hibernate.SessionFactory;
import org.project.entity.Customer;
import org.project.entity.RequestForConfirmation;
import org.project.entity.RequestForNewSpecialization;
import org.project.entity.Specialist;
import org.project.repository.ManageRepositoryForSpecialist;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ManageRepositorySpecialist implements ManageRepositoryForSpecialist {
    private final SessionFactory sessionFactory =SessionFactorySingleton.getInstance();

    @Override
    public void changeStatus(Properties properties) {
        var session = sessionFactory.getCurrentSession();
        session.update(properties);
    }



    @Override
    public void acceptSpecial(RequestForNewSpecialization request) {

    }

    @Override
    public List<RequestForNewSpecialization> findNewRequest() {
        List<RequestForNewSpecialization> request;
        var session = sessionFactory.getCurrentSession();
        var criteriaBuilder = session.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(RequestForNewSpecialization.class);
        var root = criteriaQuery.from(RequestForNewSpecialization.class);
        request= session.createQuery(criteriaQuery).list();
        return request;
    }

    @Override
    public void handleRequestForSpecialization(RequestForNewSpecialization request) {

    }

    @Override
    public List<Specialist> search(Specialist specialist) {
        var session = sessionFactory.getCurrentSession();
        var criteriaBuilder = session.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(Specialist.class);
        var root = criteriaQuery.from(Specialist.class); // select query
        List<Predicate> predicates = new ArrayList<>();
        if (specialist.getFirstName() != null && !specialist.getEmail().isEmpty())
            predicates.add(criteriaBuilder.equal(root.get("firstName"), specialist.getFirstName()));
        if (specialist.getFirstName() != null && !specialist.getEmail().isEmpty())
            predicates.add(criteriaBuilder.equal(root.get("lastName"), specialist.getFirstName()));
        if (specialist.getFirstName() != null && !specialist.getEmail().isEmpty())
            predicates.add(criteriaBuilder.equal(root.get("email"), specialist.getFirstName()));
        if (specialist.getFirstName() != null && !specialist.getEmail().isEmpty())
            predicates.add(criteriaBuilder.equal(root.get("status"), specialist.getFirstName()));
        criteriaQuery
                .where(predicates.toArray(new Predicate[0]));

        return session.createQuery(criteriaQuery).list();

    }


    @Override
    public List<RequestForConfirmation> RequestList() {
        List<RequestForConfirmation> request;
        var session = sessionFactory.getCurrentSession();
        var criteriaBuilder = session.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(RequestForConfirmation.class);
        var root = criteriaQuery.from(RequestForConfirmation.class);
        request= session.createQuery(criteriaQuery).list();
        return request;
    }

    @Override
    public void removeRequestForConf(RequestForConfirmation request) {
        var session = sessionFactory.getCurrentSession();
        var criteriaBuilder = session.getCriteriaBuilder();
        var delete = criteriaBuilder.createCriteriaDelete(RequestForConfirmation.class);
        var root = delete.from(RequestForConfirmation.class);
        delete.where(criteriaBuilder.equal(root.get("email"), request.getEmail()));

        // perform update
        session.createQuery(delete).executeUpdate();
    }

    @Override
    public void removeRequestForNewSpec(RequestForNewSpecialization request) {
        var session = sessionFactory.getCurrentSession();
        var criteriaBuilder = session.getCriteriaBuilder();
        var delete = criteriaBuilder.createCriteriaDelete(RequestForNewSpecialization.class);
        var root = delete.from(RequestForNewSpecialization.class);
//        delete.where(criteriaBuilder.equal(root.get("email"),));

        // perform update
        session.createQuery(delete).executeUpdate();

    }
}
