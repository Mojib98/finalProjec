package com.finalProject.Project.service.imp;

import com.finalProject.Project.entity.BaseClass;
import com.finalProject.Project.repository.interfaces.GenericRepository;
import com.finalProject.Project.service.interfaces.GenericService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GenericServiceImpl<T> implements GenericService<T> {
    GenericRepository<T> genericRepository;

    public GenericServiceImpl(GenericRepository<T> genericRepository) {
        this.genericRepository = genericRepository;
    }

    public GenericServiceImpl() {
    }

    @Override
    public T insert(T t) {
       return genericRepository.save(t);

    }

    @Override
    public T update(T t) {
        genericRepository.save(t);
        return null;
    }

    @Override
    public void remove(T t) {
        genericRepository.delete(t);
    }

    @Override
    public T findById(Integer id) {
//        return genericRepository.findAllById(id);
        return null;
    }

    @Override
    public List<T> findAll() {
        return null;
    }
}
