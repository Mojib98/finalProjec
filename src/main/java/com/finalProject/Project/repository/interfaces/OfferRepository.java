package com.finalProject.Project.repository.interfaces;

import com.finalProject.Project.entity.Offer;
import com.finalProject.Project.entity.enumeration.WorkStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OfferRepository extends CrudRepository<Offer,Integer> {
    @Query("from Offer where orders.customers.id=:id")
    List<Offer> find( @Param("id") Integer id);
    List<Offer> findAllByOrdersId(Integer id);
    @Query("from Offer where orders.workStatus=:work and expert.id=:id")
    List<Offer> findListOffer( @Param("id") Integer id,@Param("work") WorkStatus workStatus);
//    Offer findById(Integer id);
//    @Param("work") WorkStatus workStatus,

}
