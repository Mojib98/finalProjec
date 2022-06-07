package com.finalProject.Project.service.imp;

import com.finalProject.Project.entity.*;
import com.finalProject.Project.entity.Order;
import com.finalProject.Project.entity.dto.OrderDto;
import com.finalProject.Project.repository.interfaces.ManageRepositoryForExpert;
import com.finalProject.Project.repository.interfaces.OfferRepository;
import com.finalProject.Project.repository.interfaces.OrderRepository;
import com.finalProject.Project.service.interfaces.OfferService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
@AllArgsConstructor
public class ManagerProfileServiceImpl {
    OrderRepository orderRepository;
    ManageRepositoryForExpert manageRepositoryForExpert;
    OfferRepository offerRepository;
    public List<Offer> historyOfferForExpert(String email){
        return offerRepository.expertHistory(email);
    }
    @Transactional
    public List<Offer> historyOfferForCustomer(String email){
        return offerRepository.customerHistory(email);
    }
    public List<Order> searchOrder(OrderDto orderDto) {
        Specification<Order> specification = optionCustomer(orderDto);
        return orderRepository.findAll(specification);
//        return customerRepository.findAll(specification);
    }
    public Specification<Order> optionCustomer(OrderDto orderDto) {
        Predicate predicate;
        Specification<Order> specification = new Specification<Order>() {
            @Override
            public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                var criteriaQuery = criteriaBuilder.createQuery(Order.class);
                List<Predicate> predicates = new ArrayList<>();
                if (orderDto.getStartDate() != null  && !orderDto.getStartDate().isEmpty())
                    if (orderDto.getFinishDate() != null && !orderDto.getFinishDate().isEmpty()){
                        LocalDate startDate =LocalDate.parse(orderDto.getStartDate());
                        LocalTime timeStart = LocalTime.of(0,0,0);
                        LocalDateTime start = LocalDateTime.of(startDate,timeStart);
                        LocalDate endDate =LocalDate.parse(orderDto.getFinishDate());
                        LocalTime timeEnd = LocalTime.of(23,59,59);
                        LocalDateTime end = LocalDateTime.of(endDate,timeEnd);
                        predicates.add(criteriaBuilder.between(root.get("timeForWork"), start,end));
                    }else {
                        LocalDate startDate =LocalDate.parse(orderDto.getStartDate());
                        LocalTime timeStart = LocalTime.of(0,0,0);
                        LocalDateTime start = LocalDateTime.of(startDate,timeStart);
                        LocalDate endDate =LocalDate.parse(orderDto.getStartDate());
                        LocalTime timeEnd = LocalTime.of(23,59,59);
                        LocalDateTime end = LocalDateTime.of(endDate,timeEnd);
                        predicates.add(criteriaBuilder.between(root.get("timeForWork"), start,end));
                    }
              /*  if (orderDto.getServiceName() != null && !orderDto.getServiceName().isEmpty())
                    predicates.add(criteriaBuilder.equal(root.get("lastName"), orderDto.getServiceName()));*/
                if (orderDto.getSubServiceName() != null && !orderDto.getSubServiceName().isEmpty()){
                    Join<Order, SubService> subService = root.join("subService",JoinType.INNER);
                    var s = subService.join("service",JoinType.INNER);
                    predicates.add(criteriaBuilder.equal(s.get("name"), orderDto.getSubServiceName()));
                }
                if (orderDto.getServiceName() != null && !orderDto.getServiceName().isEmpty()){
                    Join<Order, SubService> subService = root.join("subService",JoinType.INNER);
                    Join<SubService, Service> service = subService.join("service",JoinType.INNER);
                    predicates.add(criteriaBuilder.equal(service.get("name"), orderDto.getServiceName()));
                }
                if (orderDto.getWorkStatus() != null)
                    predicates.add(criteriaBuilder.equal(root.get("workStatus"), orderDto.getWorkStatus()));
//                if (customer.() != null)
//                    predicates.add(criteriaBuilder.equal(root.get("rate"), customer.getRate()));
                criteriaQuery
                        .where(predicates.toArray(new Predicate[0]));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

            }

        };


        return specification;
    }
    public List<Expert> findBySingUpTime(LocalDateTime start,LocalDateTime end){
        return manageRepositoryForExpert.findAllByTimeBetween(start,end);
    }
    public List<Expert> findExpertBySingupTime(){
        LocalDateTime start =LocalDateTime.of(2022,5,29, 0,0,0);
        LocalDateTime end =LocalDateTime.of(2022,5,29, 23,59,59);

        return null;
    }
    public List<Customer> findCustomerBySingUpTime(){
        return null;
    }
}
