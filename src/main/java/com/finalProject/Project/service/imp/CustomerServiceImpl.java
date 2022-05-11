package com.finalProject.Project.service.imp;

import com.finalProject.Project.entity.Offer;
import com.finalProject.Project.entity.Order;
import com.finalProject.Project.entity.SubService;
import com.finalProject.Project.entity.enumeration.WorkStatus;
import com.finalProject.Project.repository.interfaces.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl {
    OfferServiceImpl offerService;
    OrderRepository orderRepository;
    ServicesServiceImpl servicesService;

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

}
