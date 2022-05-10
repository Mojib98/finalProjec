package com.finalProject.Project.service.interfaces;

import com.finalProject.Project.entity.*;
import com.finalProject.Project.entity.enumeration.WorkStatus;

import java.util.List;

public interface ExpertService{
    List<Order> findOrders(Expert expert);
    Expert findByEmail(String email);
    Customer findByIdCustomer(Integer id);
    void changeWorkFlow(Order order);
    List<Offer> findMyAcceptOffer(Expert expert);
    void changeWorkByExpert(Integer id, WorkStatus workStatus);
    void changePassword(Expert specialist, String newPassword);
}
