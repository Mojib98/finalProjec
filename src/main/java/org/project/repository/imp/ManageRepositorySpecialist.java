package org.project.repository.imp;

import org.hibernate.SessionFactory;
import org.project.entity.*;
import org.project.entity.Budget;
import org.project.entity.enumeration.UserStatus;
import org.project.repository.interfaces.ManageRepositoryForSpecialist;

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
    public void changeStatus(RequestForConfirmation request) {
        var session = sessionFactory.getCurrentSession();
        session.update(request);
    }

    @Override
    public void insertSpecial(Expert specialist) {
        var session = sessionFactory.getCurrentSession();
            session.save(specialist);
    }

    @Override
    public void unAccept(RequestForNewSpecialization request) {
        var session = sessionFactory.getCurrentSession();
        session.update(request);
    }

    @Override
    public List<RequestForNewSpecialization> findNewRequest() {
        List<RequestForNewSpecialization> request;
        var session = sessionFactory.getCurrentSession();
        var criteriaBuilder = session.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(RequestForNewSpecialization.class);
        var root = criteriaQuery.from(RequestForNewSpecialization.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("statusUser"), UserStatus.AWAITING_CONFIRMATION));
        request= session.createQuery(criteriaQuery).list();
        return request;
   /*     String hql="select new RequestForNewSpecialization (s.id,s.Time,s.name,s.Description,s.statuses,s.specialist,s.service.id) from RequestForNewSpecialization s";
        var q = session.createQuery(hql,RequestForNewSpecialization.class);
        return q.getResultList();*/
    }

    @Override
    public void handleRequestForSpecialization(Service service, Expert specialist) {
        var session = sessionFactory.getCurrentSession();
//        session.persist(specialist);
      var s=  session.find(Expert.class,specialist.getId());
        s.addService(service);
        session.update(s);
    }

    @Override
    public List<Expert> search(Expert specialist) {
        var session = sessionFactory.getCurrentSession();
        var criteriaBuilder = session.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(Expert.class);
        var root = criteriaQuery.from(Expert.class); // select query
        List<Predicate> predicates = new ArrayList<>();
        if (specialist.getFirstName() != null && !specialist.getFirstName().isEmpty())
            predicates.add(criteriaBuilder.equal(root.get("firstName"), specialist.getFirstName()));
        if (specialist.getLastName() != null && !specialist.getLastName().isEmpty())
            predicates.add(criteriaBuilder.equal(root.get("lastName"), specialist.getFirstName()));
        if (specialist.getEmail() != null && !specialist.getEmail().isEmpty())
            predicates.add(criteriaBuilder.equal(root.get("email"), specialist.getFirstName()));
       /* if (specialist.getStatus() != null && !specialist.getStatus().)
            predicates.add(criteriaBuilder.equal(root.get("status"), specialist.getFirstName()));*/
        criteriaQuery
                .where(predicates.toArray(new Predicate[0]));

        return session.createQuery(criteriaQuery).list();

    }


    @Override
    public List<RequestForConfirmation> requestList() {
        List<RequestForConfirmation> request;
        var session = sessionFactory.getCurrentSession();
        var criteriaBuilder = session.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(RequestForConfirmation.class);
        var root = criteriaQuery.from(RequestForConfirmation.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("status"), UserStatus.AWAITING_CONFIRMATION));
        request= session.createQuery(criteriaQuery).getResultList();
        return request;
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
    public void insertBudget(Budget budget){
        var session = sessionFactory.getCurrentSession();
        session.save(budget);
    }


}
