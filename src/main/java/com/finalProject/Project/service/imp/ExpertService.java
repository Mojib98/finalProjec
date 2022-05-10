package com.finalProject.Project.service.imp;

import com.finalProject.Project.entity.Customer;
import com.finalProject.Project.entity.Expert;
import com.finalProject.Project.entity.Offer;
import com.finalProject.Project.entity.Order;
import com.finalProject.Project.entity.enumeration.WorkStatus;
import com.finalProject.Project.repository.interfaces.OfferRepository;
import com.finalProject.Project.repository.interfaces.OrderRepository;
import com.finalProject.Project.repository.interfaces.SpecialistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ExpertService implements com.finalProject.Project.service.interfaces.ExpertService {
    OrderRepository orderRepository;
    SpecialistRepository specialistRepository;
    OfferRepository offerRepository;

    public ExpertService(OrderRepository orderRepository, SpecialistRepository specialistRepository, OfferRepository offerRepository) {
        this.orderRepository = orderRepository;
        this.specialistRepository = specialistRepository;
        this.offerRepository = offerRepository;
    }

    @Override
    public List<Order> findOrders(Expert expert) {
        List<Order> list = orderRepository.findMyOrder(expert.getId());
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
    public void changeWorkByExpert(Integer id, WorkStatus workStatus) {

    }

    @Override
    public void changePassword(Expert specialist, String newPassword) {

    }
}
