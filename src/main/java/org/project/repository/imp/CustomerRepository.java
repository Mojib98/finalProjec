package org.project.repository.imp;

import org.hibernate.SessionFactory;
import org.project.entity.Customer;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;


public class CustomerRepository extends GenericRepositoryImpl<Customer> implements org.project.repository.CustomerRepository {


    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    public List<Customer> search(Customer customer) {
        var session = sessionFactory.getCurrentSession();
        var criteriaBuilder = session.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(Customer.class);
        var root = criteriaQuery.from(Customer.class); // select query
        List<Predicate> predicates = new ArrayList<>();
        if (customer.getFirstName() != null && !customer.getEmail().isEmpty())
            predicates.add(criteriaBuilder.equal(root.get("firstName"), customer.getFirstName()));

        criteriaQuery
                .where(predicates.toArray(new Predicate[0]));

        return session.createQuery(criteriaQuery).list();

    }


}
