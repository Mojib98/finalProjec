package com.finalProject.Project.service.imp;

import com.finalProject.Project.entity.*;
import com.finalProject.Project.entity.enumeration.WorkStatus;
import com.finalProject.Project.repository.interfaces.CommentRepository;
import com.finalProject.Project.repository.interfaces.CustomerRepository;
import com.finalProject.Project.repository.interfaces.OrderRepository;
import com.finalProject.Project.service.interfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private OfferServiceImpl offerService;
    private OrderRepository orderRepository;
    private ServicesServiceImpl servicesService;
    private CustomerRepository customerRepository;
    private ManageExpertService expertService;
    private CommentRepository commentRepository;

    public CustomerServiceImpl(OfferServiceImpl offerService, OrderRepository orderRepository,
                               ServicesServiceImpl servicesService, CustomerRepository customerRepository,
                               ManageExpertService expertService, CommentRepository commentRepository) {
        this.offerService = offerService;
        this.orderRepository = orderRepository;
        this.servicesService = servicesService;
        this.customerRepository = customerRepository;
        this.expertService = expertService;
        this.commentRepository = commentRepository;
    }

    @Transactional
    public void insertOrder(Order order) {
        order.setWorkStatus(WorkStatus.WAIT_FOR_OFFER);
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
    public void choiceOffer(Order order, Offer offer, List<Offer> offers) {
        order.setWorkStatus(WorkStatus.WAIT_FOR_ARRIVE);
        order.setOffer(offer);
        offer.setWorkStatus(WorkStatus.START);
        orderRepository.save(order);
        offerService.removeOffer(offers);


    }

    public List<Order> myDownOrder(Integer id) {
        return orderRepository.findAllByCustomersIdAndWorkStatusEquals(id, WorkStatus.DONE);
    }

    @Transactional
    public void paying(Order order, Integer rate) {
        Order order1 = orderRepository.findById(order.getId()).get();
        Offer offer = offerService.findOfferById(order1.getOffer().getId());
        System.out.println(offer);
        Customer customer = customerRepository.findById(order1.getCustomers().getId()).get();
        Expert expert = expertService.findById(offer.getExpert().getId());
        Integer walletCustomer = customer.getBudget();
        Integer offerPrice = offer.getOfferPrice();
        Integer walletExpert = expert.getBudget();
        customer.setBudget(walletCustomer - offerPrice);
        expert.setBudget(walletCustomer + walletExpert);
        rate += expert.getRate();
        expert.setRate(rate / 2);
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
    public void addComment(Offer offer, Comment comment) {
        commentRepository.save(comment);
        Offer offer1 = offerService.findOfferById(offer.getId());
        offer1.setComment(comment);
//        offerService.insertOffer(offer);
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



    public List<Customer> search(Customer customer) {
        Specification<Customer> specification = option(customer);
        return null;
    }
}
