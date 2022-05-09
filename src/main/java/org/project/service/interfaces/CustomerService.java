package org.project.service.interfaces;

import org.project.entity.*;

import java.util.List;

public interface CustomerService extends GenericService<Orders> {

    List<Offer> AllOffer(Integer id);

    Offer findOfferById(Integer id);

    void choice(Offer offer, Orders order);

    Orders findOrderById(Integer id);

    List<Orders> findDownOrder(Integer id);

    Offer myAcceptOffer(Integer id);

    void paying(Offer offer, Integer price);

//    void addComment(AcceptOffer acceptOffer, Integer id, Comment comment);

    void changePassword(Customer customer, String newPassword);
}
