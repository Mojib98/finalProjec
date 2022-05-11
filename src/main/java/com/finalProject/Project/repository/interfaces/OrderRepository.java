package com.finalProject.Project.repository.interfaces;

import com.finalProject.Project.entity.Order;
import com.finalProject.Project.entity.enumeration.WorkStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.lang.annotation.Native;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order,Integer> {
    List<Order> findOrderForExpert(Integer id);
    @Modifying
    @Query("update Order set workStatus=:work where id=:id")
    void updateStatus(@Param("work") WorkStatus workStatus,@Param("id") Integer id);
    List<Order> findAllByCustomersId(Integer id);
    @Modifying
    @Query("update Order set workStatus=:work where offer.id=:id")
    void updateStatusByOfferId(@Param("work") WorkStatus workStatus,@Param("id") Integer id);

 /*   @Query("select new com.finalProject.Project.entity.Order(o.id,o.time,o.offerPrice,o.timeForWork,o.address," +
            "o.describe,o.customers,o.subService)" +
            " from Order o inner join Specialty p on o.subService.category.id=p.service.id " +
            "where p.expert.id=:id ")
    List<Order> findMyOrder(@Param(value = "id") Integer id);*/
/*    @@Native("select * from orders o inner join sub_service ss on ss.id = o.sub_service_id\n" +
            "    inner join service s on s.id = ss.category_id\n" +
            "    inner join specialty s2 on s.id = s2.service_id where s2.id=19;")
    List<Order> findMyOrder(@Param(value = "id") Integer id);*/
 /*   select * from orders o inner join sub_service ss on ss.id = o.sub_service_id
    inner join service s on s.id = ss.category_id
    inner join specialty s2 on s.id = s2.service_id where s2.id=19;*/

/*
    @Query("from Order o inner join Offer f where " +
            "f.expert.id=:id and o.workStatus='WAIT_FOR_ARRIVE'")
    List<Order> myAcceptOffer(@Param(value = "id") Integer id);
*/
}
