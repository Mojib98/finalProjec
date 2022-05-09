package org.project.service.imp;

import org.hibernate.SessionFactory;
import org.project.entity.BaseClass;
import org.project.repository.interfaces.GenericRepository;
import org.project.repository.imp.GenericRepositoryImpl;
import org.project.repository.imp.SessionFactorySingleton;
import org.project.service.interfaces.GenericService;

import java.util.List;

public abstract class GenericServiceImpl<T> implements GenericService<T> {
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    private final GenericRepository<T> genericRepository = new GenericRepositoryImpl<>();

    @Override
    public T insert(T t) {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                genericRepository.insert(t);
                transaction.commit();
                return t;
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
                return null;
            }
        }
    }

    @Override
    public T update(T t) {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                genericRepository.update(t);
                transaction.commit();
                return t;
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
                return null;
            }
        }
    }

    @Override
    public void remove(T t) {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                genericRepository.remove(t);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public T findById(Integer id) {
        T t = null;

        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                t=genericRepository.findById(id);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
                return null;
            }
        }
        return t;
    }

    @Override
    public List<T> findAll() {
        List<T> list;
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                list = genericRepository.findAll();
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
                return null;
            }
        }
        return list;
    }

}
