package org.project.repository.interfaces;

import org.project.entity.Customer;
import org.project.entity.Service;
import org.project.entity.SubService;

import java.util.List;

public interface RepositoryService {
    SubService findByName(String name);
    List<SubService> findByCategory(String name);
}
