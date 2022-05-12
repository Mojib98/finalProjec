package com.finalProject.Project.service.imp;

import com.finalProject.Project.entity.*;
import com.finalProject.Project.entity.enumeration.UserStatus;
import com.finalProject.Project.entity.enumeration.WorkStatus;
import com.finalProject.Project.repository.interfaces.OfferRepository;
import com.finalProject.Project.repository.interfaces.OrderRepository;
import com.finalProject.Project.repository.interfaces.SpecialtyRepository;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class ExpertService implements com.finalProject.Project.service.interfaces.ExpertService {
    OrderRepository orderRepository;
    SpecialtyRepository specialistRepository;
    OfferRepository offerRepository;
    @Autowired
    OfferServiceImpl offerService;


    public ExpertService(OrderRepository orderRepository, SpecialtyRepository specialistRepository, OfferRepository offerRepository) {
        this.orderRepository = orderRepository;
        this.specialistRepository = specialistRepository;
        this.offerRepository = offerRepository;
    }

    @Override
    @Transactional
    public List<Order> findOrders(Expert expert) {
        List<Order> list = orderRepository.findOrderForExpert(expert.getId());
        return list;
    }

    @Override
    public Expert findByEmail(String email) {
        return null;
    }

    @Override
    public Customer findByIdCustomer(Integer id) {
        return null;
    }

    @Override
    public void changeWorkFlow(Order order) {

    }

    @Override
    public List<Offer> findMyAcceptOffer(Expert expert) {
        return null;
    }

    @Override
    @Transactional
    public void changeWorkByExpert(Integer id, WorkStatus workStatus) {
        orderRepository.updateStatusByOfferId(workStatus,id);
    }

    @Override
    public void changePassword(Expert specialist, String newPassword) {

    }
    @Transactional
    public void requestForSpecialty(Specialty specialty){
        specialty.setStatus(UserStatus.AWAITING_CONFIRMATION);
        specialistRepository.save(specialty);
    }
    @Transactional
    public void insertOffer(Offer offer,Order order){
        order.setWorkStatus(WorkStatus.WAIT_FOR_CHOICE);
        offerRepository.save(offer);
        orderRepository.updateStatus(WorkStatus.WAIT_FOR_CHOICE,order.getId());

    }

    public List<Offer> findOfferForAction(Integer id,WorkStatus workStatus){
        return offerService.findExpertOfferForAction(id,workStatus);
    }
 /*   public List<Order> findOrderForStart(Expert expert){

    }*/
}
