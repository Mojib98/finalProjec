package org.project.service.interfaces;

import org.project.entity.Customer;
import org.project.entity.Orders;
import org.project.entity.Expert;
import org.project.entity.enumeration.WorkStatus;

import java.util.List;

public interface SpecialistService {
    List<Orders> findOrders(Expert specialist);
    Expert findByEmail(String email);
    Customer findByIdCustomer(Integer id);
    void changeWorkFlow(Orders order);
    void changeWorkBySpecialist(Integer id, WorkStatus workStatus);
    void changePassword(Expert specialist, String newPassword);
}
