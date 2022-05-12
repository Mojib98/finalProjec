package com.finalProject.Project.service.interfaces;

import com.finalProject.Project.entity.*;


import java.util.List;

public interface CustomerService {
    public void insertOrder(Order order);
    List<SubService> shoeSubService();
    List<Order> findMyOrder(Integer id);
    void choiceOffer(Order order, Offer offer, List<Offer> offers);
    List<Order> myDownOrder(Integer id);
    void paying(Order order,Integer rate);
    List<com.finalProject.Project.entity.SubService> allSubService();
    List<Offer> findOfferByOrderId(Integer id);
    void addComment(Offer offer, Comment comment);
    List<Offer> findByRateAndPrice();
    List<Customer> search(Customer customer);
    void changePassword(Customer customer, String newPassword);


}
