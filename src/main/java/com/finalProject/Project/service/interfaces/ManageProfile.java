package com.finalProject.Project.service.interfaces;

import com.finalProject.Project.entity.*;
import com.finalProject.Project.entity.dto.OrderDto;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.List;

public interface ManageProfile {
    List<Offer> historyOfferForExpert(String email);

    List<Offer> historyOfferForCustomer(String email);

    List<Order> searchOrder(OrderDto orderDto);

    Specification<Order> optionCustomer(OrderDto orderDto);

    List<Expert> findBySingUpTime(LocalDateTime start, LocalDateTime end);

    List<Expert> findExpertBySingupTime();

    List<Customer> findCustomerBySingUpTime();

    List<User> findByDate(String date);

    List<Customer> findCustomerByOrderNumber(Long number);

    List<Expert> findExpertByOrderNumber(long number);
}
