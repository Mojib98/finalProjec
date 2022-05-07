package org.project.service.interfaces;

import org.project.entity.AcceptOffer;
import org.project.entity.Customer;
import org.project.entity.Order;
import org.project.entity.Expert;
import org.project.entity.enumeration.WorkStatus;

import java.util.List;

public interface SpecialistService {
    List<Order> findOrders(Expert specialist);
    Expert findByEmail(String email);
    Customer findByIdCustomer(Integer id);
    void changeWorkFlow(Order order);
    List<AcceptOffer> findMyAcceptOffer(Integer id);
    void changeWorkBySpecialist(Integer id, WorkStatus workStatus);
    void changePassword(Expert specialist, String newPassword);
}
