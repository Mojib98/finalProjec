package org.project.service.interfaces;

import org.project.entity.*;
import org.project.entity.enumeration.WorkStatus;

import java.util.List;

public interface CustomerService extends GenericService<Order> {
    List<Customer> search(Customer customer);

    List<Offer> AllOffer(Integer id);

    Offer findOfferById(Integer id);

    void choice(Offer offer, AcceptOffer acceptOffer);

    Order findOrderById(Integer id);

    List<Order> findDownOrder(Integer id);

    AcceptOffer myAcceptOffer(Integer id);

    void paying(AcceptOffer acceptOffer, Integer id);

    void addComment(AcceptOffer acceptOffer, Integer id, Comment comment);

    void changePassword(Customer customer, String newPassword);
}
