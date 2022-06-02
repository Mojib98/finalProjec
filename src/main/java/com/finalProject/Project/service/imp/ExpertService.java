package com.finalProject.Project.service.imp;

import com.finalProject.Project.entity.*;
import com.finalProject.Project.entity.enumeration.UserStatus;
import com.finalProject.Project.entity.enumeration.WorkStatus;
import com.finalProject.Project.repository.interfaces.ManageRepositoryForExpert;
import com.finalProject.Project.repository.interfaces.OfferRepository;
import com.finalProject.Project.repository.interfaces.OrderRepository;
import com.finalProject.Project.repository.interfaces.SpecialtyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExpertService implements com.finalProject.Project.service.interfaces.ExpertService {
    private final OrderRepository orderRepository;
    private final SpecialtyRepository specialistRepository;
    private final OfferRepository offerRepository;
    private final OfferServiceImpl offerService;
    private final ServicesServiceImpl servicesService;
    private final ManageRepositoryForExpert manageRepositoryForExpert;

    public ExpertService(OrderRepository orderRepository, SpecialtyRepository specialistRepository,
                         OfferRepository offerRepository, OfferServiceImpl offerService, ServicesServiceImpl service, ManageRepositoryForExpert manageRepositoryForExpert) {
        this.orderRepository = orderRepository;
        this.specialistRepository = specialistRepository;
        this.offerRepository = offerRepository;
        this.offerService = offerService;
        this.servicesService = service;
        this.manageRepositoryForExpert = manageRepositoryForExpert;
    }

    @Override
    @Transactional
    public List<Order> findOrders(Expert expert) {
        List<Order> list = orderRepository.findOrderForExpert(expert.getId());
        return list;
    }

    @Override
    @Transactional
    public void changeWorkByExpert(Integer id, WorkStatus workStatus) {
        orderRepository.updateStatusByOfferId(workStatus, id);
    }
    @Transactional
    public void startWork(Integer id) {
//        Integer ids=Integer.parseInt(id);
//        System.out.println(ids);
        orderRepository.updateStatus(WorkStatus.START, id);
    }
    @Transactional
    public void doneWork(Integer id) {
//        Integer ids=Integer.parseInt(id);
//        System.out.println(ids);
        orderRepository.updateStatus(WorkStatus.DONE, id);
    }

    @Override
    public void changePassword(Expert specialist, String newPassword) {

    }

    @Transactional
    public void requestForSpecialty(Expert expert, String serviceDto) {
        com.finalProject.Project.entity.Service service = servicesService.findServiceByName(serviceDto);
        Specialty specialty = new Specialty(null,null,expert,service);
        specialty.setService(service);
        specialty.setStatus(UserStatus.AWAITING_CONFIRMATION);
        specialistRepository.save(specialty);
    }

    @Transactional
    public void insertOffer(Offer offer, Order order,Expert expert) {
//        order.setWorkStatus(WorkStatus.WAIT_FOR_CHOICE);
        offer.setExpert(expert);
        offer.setWorkStatus(WorkStatus.WAIT);
        offer.setOrders(order);
        offerRepository.save(offer);
        orderRepository.updateStatus(WorkStatus.WAIT_FOR_CHOICE, order.getId());

    }

    public List<Offer> findOfferForAction(Integer id, WorkStatus workStatus) {
        return offerService.findExpertOfferForAction(id, workStatus);
    }
    public Order findOrderById(Integer id){
        Order order = orderRepository.findById(id).get();
        if (order == null){
            throw new RuntimeException("order not find");
        }
        return order;
    }
    @Transactional
    public List<Order> findOrdersForStart(Expert expert) {
        List<Order> list = orderRepository.findOrderForExpertStart(expert.getId());
        System.out.println(list);
        return list;
    }
    public List<Order> findOrderForFinish(Expert expert) {
        List<Order> list = orderRepository.findOrderForExpertDown(expert.getId());
        System.out.println(list);
        return list;
    }
    @Transactional
    public Expert showMyInfo(Integer id){
        return manageRepositoryForExpert.findById(id).get();
    }
    public List<Order> historyOfOrrder(Integer id){
        return orderRepository.findOrderForExpertHistory(id);
    }

}
