package com.finalProject.Project.repository.interfaces;

import com.finalProject.Project.entity.Customer;
import com.finalProject.Project.entity.Expert;
import com.finalProject.Project.entity.Offer;
import com.finalProject.Project.entity.Order;
import com.finalProject.Project.entity.enumeration.WorkStatus;

import java.util.List;

public interface ExpertRepository {
    List<Order> findOrders(Expert expert);
    Expert findByEmail(String email);
    Customer findByIdCustomer(Integer id);
    void changeWorkFlow(Order order);
    List<Offer> findMyAcceptOffer(Expert expert);
    void changeWorkByExpert(Integer id, WorkStatus workStatus);
    void changePassword(Expert specialist, String newPassword);
}
