package com.finalProject.Project.service.interfaces;

import com.finalProject.Project.entity.*;
import com.finalProject.Project.entity.dto.OrderDto;


import java.util.List;

public interface CustomerService {
    public void insertOrder(OrderDto order,Customer customer);
    List<SubService> shoeSubService();
    List<Order> findMyOrder(Integer id);
    void choiceOffer(Integer id);
    List<Order> myDownOrder(Integer id);
    void paying(OrderDto orderDto);
    List<com.finalProject.Project.entity.SubService> allSubService();
    List<Offer> findOfferByOrderId(Integer id);
    void addComment(String Comment, Offer offer,Customer customer);
    List<Offer> findByRateAndPrice();
    List<Customer> search(Customer customer);
    void changePassword(Customer customer, String newPassword);


}
