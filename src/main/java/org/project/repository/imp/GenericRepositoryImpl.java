package org.project.repository.imp;

import org.hibernate.SessionFactory;

import java.util.List;

public class GenericRepositoryImpl<T> implements org.project.repository.GenericRepository<T> {
    private Class<T> clazz;
    private final SessionFactory sessionFactory =SessionFactorySingleton.getInstance();



    @Override
    public T insert(T t) {
        var session = sessionFactory.getCurrentSession();
         session.save(t);
        return t;
    }

    @Override
    public T update(T t) {
        var session = sessionFactory.getCurrentSession();
        session.update(t);
        return t;
    }

    @Override
    public void remove(T t) {
        var session = sessionFactory.getCurrentSession();
        session.remove(t);
    }


    @Override
    public T findById(Integer id) {
        return sessionFactory.getCurrentSession().find(clazz,id);
    }

    @Override
    public List<T> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from " + clazz.getSimpleName(), clazz)
                .getResultList();
    }
}
