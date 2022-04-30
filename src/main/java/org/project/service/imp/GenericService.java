package org.project.service.imp;

import org.hibernate.SessionFactory;
import org.project.repository.GenericRepository;
import org.project.repository.imp.GenericRepositoryImpl;
import org.project.repository.imp.SessionFactorySingleton;

import java.util.List;

public class GenericService<T> implements org.project.service.GenericService<T> {
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    private final GenericRepository<T> genericRepository = new GenericRepositoryImpl<>();
    @Override
    public T insert(T t) {
        try (var session = sessionFactory.getCurrentSession()){
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
        try (var session = sessionFactory.getCurrentSession()){
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
    public void remove(T t) {
        try (var session = sessionFactory.getCurrentSession()){
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

        try (var session = sessionFactory.getCurrentSession()){
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                return genericRepository.findById(id);
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
                return null;
            }
        }
    }

    @Override
    public List<T> findAll() {
        List<T> list;
        try (var session = sessionFactory.getCurrentSession()){
            var transaction = session.getTransaction();
            try {
                transaction.begin();
               list= genericRepository.findAll();
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
