package org.project.service.interfaces;

import java.util.List;

public interface GenericService<T> {
    T insert(T t);
    T update(T t);
    void remove(T t);
    T findById(Integer id);
    List<T> findAll();
}
