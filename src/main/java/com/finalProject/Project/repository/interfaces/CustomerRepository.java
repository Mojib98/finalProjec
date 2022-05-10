package com.finalProject.Project.repository.interfaces;

import com.finalProject.Project.entity.*;
import com.finalProject.Project.entity.enumeration.WorkStatus;

import java.util.List;

public interface CustomerRepository<T extends BaseClass> {
    List<Offer> findAllOffer(Integer id);
    Offer findOfferById(Integer id);
//    AcceptOffer insertAcceptOfferAcceptOffer acceptOffer);
    Order findOrder(Integer id);
    Expert find(Integer id);
    List<Order> findDownOrder(Integer id);
//    AcceptOffer findAcceptOrder(Integer id);
    Customer findCustomer(Integer id);
    void insertComment(Comment comment);
    void changePassword(Customer customer,String password);
    void changeWorkBySpecialist(Integer id, WorkStatus workStatus);
    void payIng(Customer customer, Expert specialist);


    
}
