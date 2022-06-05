package com.finalProject.Project.service.imp;

import com.finalProject.Project.entity.Customer;
import com.finalProject.Project.entity.Offer;
import com.finalProject.Project.entity.Order;
import com.finalProject.Project.entity.dto.OrderDto;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ManagerProfileServiceImpl {
    public List<Offer> historyOfferForExpert(String email){
        return null;
    }
    public List<Offer> historyOfferForCustomer(String email){
        return null;
    }
    public List<Order> searchCustomer(OrderDto orderDto) {
        Specification<Order> specification = optionCustomer(orderDto);
        return null;
//        return customerRepository.findAll(specification);
    }
    public Specification<Order> optionCustomer(OrderDto orderDto) {
        Predicate predicate;
        Specification<Order> specification = new Specification<Order>() {
            @Override
            public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                var criteriaQuery = criteriaBuilder.createQuery(Order.class);
                List<Predicate> predicates = new ArrayList<>();
             /*   if (orderDto.getFirstName() != null && !orderDto.getFirstName().isEmpty())
                    predicates.add(criteriaBuilder.equal(root.get("firstName"), orderDto.getFirstName()));*/
                if (orderDto.getServiceName() != null && !orderDto.getServiceName().isEmpty())
                    predicates.add(criteriaBuilder.equal(root.get("lastName"), orderDto.getServiceName()));
                if (orderDto.getSubServiceName() != null && !orderDto.getSubServiceName().isEmpty())
                    predicates.add(criteriaBuilder.equal(root.get("email"), orderDto.getSubServiceName()));
                if (orderDto.getWorkStatus() != null)
                    predicates.add(criteriaBuilder.equal(root.get("status"), orderDto.getWorkStatus()));
//                if (customer.() != null)
//                    predicates.add(criteriaBuilder.equal(root.get("rate"), customer.getRate()));
                criteriaQuery
                        .where(predicates.toArray(new Predicate[0]));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

            }

        };


        return specification;
    }
}
