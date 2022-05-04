package org.project.repository.interfaces;

import org.project.entity.BaseClass;

import java.util.List;

public interface GenericRepository<T> {
    T insert(T t);
    T update(T t);
    void remove(T t);
    T findById(Integer id);
    List<T> findAll();

}
