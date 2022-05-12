package com.finalProject.Project.service.imp;

import com.finalProject.Project.entity.*;
import com.finalProject.Project.entity.enumeration.WorkStatus;
import com.finalProject.Project.repository.interfaces.CustomerRepository;
import com.finalProject.Project.repository.interfaces.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl {
    OfferServiceImpl offerService;
    OrderRepository orderRepository;
    ServicesServiceImpl servicesService;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ManageExpertService expertService;


    public CustomerServiceImpl(OfferServiceImpl offerService, OrderRepository orderRepository, ServicesServiceImpl servicesService) {
        this.offerService = offerService;
        this.orderRepository = orderRepository;
        this.servicesService = servicesService;
    }

    public void insertOrder(Order order){
        order.setWorkStatus(WorkStatus.WAIT_FOR_OFFER);
        orderRepository.save(order);
    }
    public List<SubService> shoeSubService(){
        return servicesService.showAllSubService();
    }
    public List<Order> findMyOrder(Integer id){
        return orderRepository.findAllByCustomersId(id);
    }
    @Transactional
    public void choiceOffer(Order order, Offer offer, List<Offer> offers){
        order.setWorkStatus(WorkStatus.WAIT_FOR_ARRIVE);
        order.setOffer(offer);
        offer.setWorkStatus(WorkStatus.START);
        orderRepository.save(order);
        offerService.removeOffer(offers);


    }
    public List<Order> myDownOrder(Integer id){
        return orderRepository.findAllByCustomersIdAndWorkStatusEquals(id,WorkStatus.DONE);
    }
    @Transactional
    public void paying(Order order){
        Order order1 = orderRepository.findById(order.getId()).get();
        Offer offer = offerService.findOfferById(order1.getOffer().getId());
        System.out.println(offer);
        Customer customer =customerRepository.findById(order1.getCustomers().getId()).get();
        Expert expert=expertService.findById(offer.getExpert().getId());
        Integer p=customer.getBudget();
        Integer g = offer.getOfferPrice();
        Integer e = expert.getBudget();
        customer.setBudget(p-g);
        expert.setBudget(e+g);

//        Customer customer

    }
    public List<com.finalProject.Project.entity.SubService> allSubService(){
        return servicesService.showAllSubService();
    }
    public List<Offer> findOfferByOrderId(Integer id){
        return offerService.findMyOffers(id);
    }
}
