package com.finalProject.Project.repository.interfaces;

import com.finalProject.Project.entity.SubService;

import java.util.List;

public interface RepositoryService {
    SubService findByName(String name);
    List<SubService> findByCategory(String name);
}
