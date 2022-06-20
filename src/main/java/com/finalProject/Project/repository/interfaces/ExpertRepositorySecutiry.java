package com.finalProject.Project.repository.interfaces;

import com.finalProject.Project.entity.Customer;
import com.finalProject.Project.entity.Expert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExpertRepositorySecutiry extends JpaRepository<Expert,Integer> {
    Optional<Expert> findByEmail(String email);

}
