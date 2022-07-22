package com.finalProject.Project.service.imp;

import com.finalProject.Project.entity.*;
import com.finalProject.Project.entity.dto.OrderDto;
import com.finalProject.Project.entity.enumeration.WorkStatus;
import com.finalProject.Project.repository.interfaces.CommentRepository;
import com.finalProject.Project.repository.interfaces.CustomerRepository;
import com.finalProject.Project.repository.interfaces.OrderRepository;
import com.finalProject.Project.service.interfaces.CustomerService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final OfferServiceImpl offerService;
    private final OrderRepository orderRepository;
    private final ServicesServiceImpl servicesService;
    private final CustomerRepository customerRepository;
    private final ManageExpertServiceimp expertService;
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public void insertOrder(OrderDto orderDto,Customer customer) {
        LocalDateTime localDateTime = LocalDateTime.parse(orderDto.getLocalDateTime());
        Order order = modelMapper.map(orderDto,Order.class);
        order.setTimeForWork(localDateTime);
        order.setCustomers(customer);
        order.setWorkStatus(WorkStatus.WAIT_FOR_OFFER);
        SubService subService = new SubService();
        subService.setId(orderDto.getSubServiceId());
        order.setSubService(subService);
        order.setOffer(null);
        orderRepository.save(order);
    }

    public List<SubService> shoeSubService() {
        return servicesService.showAllSubService();
    }

    @Transactional
    public List<Order> findMyOrder(Integer id) {
        return orderRepository.findAllByCustomersId(id);
    }
    @Transactional
    public List<Order> findMyOrderstatus(Integer id) {
        return orderRepository.findAllByCustomersIdAndWorkStatusEquals(id,WorkStatus.WAIT_FOR_CHOICE);
    }

    @Transactional
    public void choiceOffer(Integer id) {
        Offer offer = offerService.findOfferById(id);
        Order order=orderRepository.findById(offer.getOrders().getId()).get();
        order.setWorkStatus(WorkStatus.WAIT_FOR_ARRIVE);
        order.setOffer(offer);
        offer.setWorkStatus(WorkStatus.START);
        orderRepository.save(order);
//        offerService.removeOffer(offers);


    }
    @Transactional
    public List<Order> myDownOrder(Integer id) {
        return orderRepository.findAllOrderByCustomerIdandWorkStatus(WorkStatus.DONE,id);
    }

    @Transactional
    public void paying(OrderDto orderDto) {
        Float rate = orderDto.getRate();
        Order order1 = orderRepository.findById(orderDto.getId()).get();
        Offer offer = offerService.findOfferById(order1.getOffer().getId());
        System.out.println(offer);
        Customer customer = customerRepository.findById(order1.getCustomers().getId()).get();
        Expert expert = expertService.findById(offer.getExpert().getId());
        Integer walletCustomer = customer.getWallet();
        Integer offerPrice = offer.getOfferPrice();
        Integer walletExpert = expert.getWallet();
        customer.setWallet(walletCustomer - offerPrice);
        double salary= (offerPrice * 0.7);
        expert.setWallet((int) (salary + walletExpert));
        rate = Float.sum(expert.getRate(),rate);
        expert.setRate(rate/ 2F);
        offer.setWorkStatus(WorkStatus.PAYED);
        order1.setWorkStatus(WorkStatus.PAYED);

    }

    public List<com.finalProject.Project.entity.SubService> allSubService() {
        return servicesService.showAllSubService();
    }

    public List<Offer> findOfferByOrderId(Integer id) {
        return offerService.findMyOffers(id);
    }

    @Transactional
    public void addComment(String comment,Customer customer,Integer orderId) {
        System.out.println(comment);
        if (comment != null) {
            Comment comment1 = new Comment();
            comment1.setComment(comment);
            comment1.setCustomer(customer);
            commentRepository.save(comment1);
            Offer offer1 = offerService.findOfferByOrderId(orderId);
            offer1.setComment(comment1);
//        offerService.insertOffer(offer);
        } else return;
    }

    @Override
    public List<Offer> findByRateAndPrice() {
        Sort sortOrder = Sort.by("email");
        Sort firstNameSort = Sort.by("first_name");

        Sort groupBySort = sortOrder.and(firstNameSort);
        return null;
    }
    public Specification<Customer> option(Customer customer) {
        Predicate predicate;
        Specification<Customer> specification = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                var criteriaQuery = criteriaBuilder.createQuery(Customer.class);
                List<Predicate> predicates = new ArrayList<>();
                if (customer.getFirstName() != null && !customer.getFirstName().isEmpty())
                    predicates.add(criteriaBuilder.equal(root.get("firstName"), customer.getFirstName()));
                if (customer.getLastName() != null && !customer.getLastName().isEmpty())
                    predicates.add(criteriaBuilder.equal(root.get("lastName"), customer.getLastName()));
                if (customer.getEmail() != null && !customer.getEmail().isEmpty())
                    predicates.add(criteriaBuilder.equal(root.get("email"), customer.getEmail()));
                if (customer.getStatus() != null)
                    predicates.add(criteriaBuilder.equal(root.get("status"), customer.getStatus()));
                criteriaQuery
                        .where(predicates.toArray(new Predicate[0]));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

            }

        };


        return specification;
    }


    @Transactional
    public List<Customer> search(Customer customer) {
        Specification<Customer> specification = option(customer);
        return null;
    }



    @Transactional
    public void changePassword(Customer customer, String newPassword) {
        customerRepository.updatePassword(newPassword,customer.getEmail(),customer.getPassword());
    }
    @Transactional
    public Customer findMe(Integer id ){
        return customerRepository.findById(id).get();
    }
    @Transactional
    public void depositWallet(Integer id,Integer amount){
        Customer customer =customerRepository.findById(id).get();
        Integer oldAmount =customer.getWallet();
        customer.setWallet(oldAmount+amount);
        System.out.println(customer.getWallet());
    }
    @Transactional
    public Integer OfferPrice(Integer id){
        Order order1 = orderRepository.findById(id).get();
        Offer offer = offerService.findOfferById(order1.getOffer().getId());
        return offer.getOfferPrice();
    }
    @Transactional
    public List<Order> findPaidOrder(Integer id) {
        return orderRepository.findAllOrderByCustomerIdandWorkStatus(WorkStatus.PAYED,id);
    }

}
