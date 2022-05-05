package org.project.repository.imp;

import org.hibernate.SessionFactory;
import org.project.entity.Customer;
import org.project.entity.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class RepositoryService implements org.project.repository.interfaces.RepositoryService {
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
    public List<Customer> search(Customer customer) {
        var session = sessionFactory.getCurrentSession();
        var criteriaBuilder = session.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(Customer.class);
        var root = criteriaQuery.from(Customer.class); // select query
        List<Predicate> predicates = new ArrayList<>();
        if (customer.getFirstName() != null && !customer.getFirstName().isEmpty())
            predicates.add(criteriaBuilder.equal(root.get("firstName"), customer.getFirstName()));
        if (customer.getLastName() != null && !customer.getLastName().isEmpty())
            predicates.add(criteriaBuilder.equal(root.get("lastName"), customer.getFirstName()));
        if (customer.getEmail() != null && !customer.getEmail().isEmpty())
            predicates.add(criteriaBuilder.equal(root.get("email"), customer.getFirstName()));
        if (customer.getStatus() != null)
            predicates.add(criteriaBuilder.equal(root.get("status"), customer.getFirstName()));
        criteriaQuery
                .where(predicates.toArray(new Predicate[0]));

        return session.createQuery(criteriaQuery).list();

    }
}
