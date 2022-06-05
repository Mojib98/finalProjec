package com.finalProject.Project.repository.interfaces;

import com.finalProject.Project.entity.Comment;
import com.finalProject.Project.entity.Customer;
import com.finalProject.Project.entity.Offer;
import com.finalProject.Project.entity.Order;
import com.finalProject.Project.entity.enumeration.WorkStatus;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.lang.annotation.Native;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order,Integer> , JpaSpecificationExecutor<Order> {
    List<Order> findOrderForExpert(Integer id);
    List<Order> findOrderForExpertStart(Integer id);
    List<Order> findOrderForExpertDown(Integer id);
    List<Order> findOrderForExpertHistory(Integer id);
    @Modifying
    @Query("update Order set workStatus=:work where id=:id")
    void updateStatus( @Param("work") WorkStatus workStatus,@Param("id") Integer id);
    List<Order> findAllByCustomersId(Integer id);
    @Modifying
    @Query("update Order set workStatus=:work where offer.id=:id")
    void updateStatusByOfferId(@Param("work") WorkStatus workStatus,@Param("id") Integer id);
    List<Order> findAllByCustomersIdAndWorkStatusEquals(Integer id,WorkStatus workStatus);
    @Modifying
    @Query("update Expert set password=:password where email=:email and password=:pass")
    void updatePassword(@Param(value = "password") String password,
                          @Param(value = "email") String email,
                          @Param(value = "pass") String pass);

    @Query(" select new com.finalProject.Project.entity.Order(s.id,s.offer.offerPrice,s.timeForWork,s.describe,s.offer.expert.lastName) from Order s " +
            " where s.customers.id=:id and s.workStatus=:work")
    List<Order> findAllDownOrderByCustomerId(@Param("work") WorkStatus workStatus, @Param("id") Integer id);
    List<Order> findAll(Specification<Order> spec);


    //

}
