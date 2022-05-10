package com.finalProject.Project.repository.interfaces;

import com.finalProject.Project.entity.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order,Integer> {


    @Query("select new Order(o.id,o.time,o.offerPrice,o.timeForWork,o.address," +
            "o.describe,o.customers,o.subService)" +
            " from Order o inner join Specialty p on o.subService.category.id=p.service.id " +
            "where p.expert.id=:id ")
    List<Order> findMyOrder(@Param(value = "id") Integer id);

    @Query("from Order o inner join Offer f where " +
            "f.expert.id=:id and o.workStatus='WAIT_FOR_ARRIVE'")
    List<Order> myAcceptOffer(@Param(value = "id") Integer id);

}
