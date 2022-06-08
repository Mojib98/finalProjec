package com.finalProject.Project.repository.interfaces;

import com.finalProject.Project.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepositorySecurity extends JpaRepository<Customer,Integer> {
    Optional<Customer> findByEmail(String email);

}
