package com.finalProject.Project.repository.interfaces;

import com.finalProject.Project.entity.Customer;
import com.finalProject.Project.entity.Expert;
import com.finalProject.Project.entity.Order;
import com.finalProject.Project.entity.enumeration.WorkStatus;

import java.util.List;

public interface SpecialistRepository {
    List<Order> findOrders(Integer id);
    Expert findByEmail(String email);
    Customer findByIdCustomer(Integer id);
    void changeWorkFlow(Order order);
    void changeWorkBySpecialist(Integer id, WorkStatus workStatus);
    void changePassword(Expert specialist, String password);
}
