package com.finalProject.Project.service.interfaces;

import com.finalProject.Project.entity.*;
import com.finalProject.Project.entity.enumeration.WorkStatus;

import java.util.List;

public interface ExpertService{
    List<Order> findOrders(Expert expert);
    void changeWorkByExpert(Integer id, WorkStatus workStatus);
    void changePassword(Expert specialist, String newPassword);
    void requestForSpecialty(Expert expert,String serviceName);
    void insertOffer(Offer offer,Order order);
    List<Offer> findOfferForAction(Integer id,WorkStatus workStatus);
}
