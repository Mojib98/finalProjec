package org.project.repository.interfaces;

import org.project.entity.Customer;
import org.project.entity.Expert;
import org.project.entity.Offer;
import org.project.entity.Order;
import org.project.entity.enumeration.WorkStatus;
import org.project.service.interfaces.GenericService;

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
