package org.project.service.interfaces;

import org.project.entity.*;
import org.project.entity.enumeration.WorkStatus;

import java.util.List;

public interface ExpertService extends GenericService<Offer>{
    List<Order> findOrders(Expert expert);
    Expert findByEmail(String email);
    Customer findByIdCustomer(Integer id);
    void changeWorkFlow(Order order);
    List<Offer> findMyAcceptOffer(Expert expert);
    void changeWorkByExpert(Integer id, WorkStatus workStatus);
    void changePassword(Expert specialist, String newPassword);
}
