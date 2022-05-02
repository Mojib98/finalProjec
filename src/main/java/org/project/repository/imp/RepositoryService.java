package org.project.repository.imp;

import org.hibernate.SessionFactory;
import org.project.entity.RequestForConfirmation;
import org.project.entity.Service;

import java.util.List;

public class RepositoryService {
    private final SessionFactory sessionFactory =SessionFactorySingleton.getInstance();
    public Service findByName(String name){
        var session = sessionFactory.getCurrentSession();
        var criteriaBuilder = session.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(Service.class);
        var root = criteriaQuery.from(Service.class);
        criteriaQuery.select( root ).
                where( criteriaBuilder.equal( root.get("name"),name));
        return session.createQuery(criteriaQuery).uniqueResult();
    }
    public List<Service> findByCategory(String name){
        var session = sessionFactory.getCurrentSession();
        String hql="from Service s where s.category.name=:name";
        var query = session.createQuery(hql,Service.class)
                .setParameter("name",name);
        return query.getResultList();
    }
    public List<Service> findJustCategory(){
        var session = sessionFactory.getCurrentSession();
        String hql="from Service s where s.category is NULL ";
        var query = session.createQuery(hql,Service.class);
        return query.getResultList();
    }
    public List<Service> findJustSpecialty(){
        var session = sessionFactory.getCurrentSession();
        String hql="from Service s where s.category is not null ";
        var query = session.createQuery(hql,Service.class);
        return query.getResultList();
    }
}
