package com.finalProject.Project.repository.interfaces;

import com.finalProject.Project.entity.Customer;
import com.finalProject.Project.entity.Offer;
import com.finalProject.Project.entity.enumeration.WorkStatus;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OfferRepository extends CrudRepository<Offer,Integer> {
    @Query("from Offer where orders.customers.id=:id")
    List<Offer> find( @Param("id") Integer id);
    List<Offer> findAllByOrdersId(Integer id);
    @Query("from Offer where orders.workStatus=:work and expert.id=:id")
    List<Offer> findListOffer( @Param("id") Integer id, @Param("work") WorkStatus workStatus);
    @Query("select f from Offer  f where f.orders.customers.id=:id and f.workStatus=:work order by f.offerPrice, f.expert.rate desc ")
    List<Offer> sortByPriceAndRate( @Param("id") Integer id, @Param("work") WorkStatus workStatus);
    @Query("select f from Offer  f where f.orders.customers.id=:id and f.workStatus=:work order by f.offerPrice")
    List<Offer> sortByPrice( @Param("id") Integer id, @Param("work") WorkStatus workStatus);
    @Query("select f from Offer  f where f.orders.customers.id=:id and f.workStatus=:work order by  f.expert.rate desc ")
    List<Offer> sortByRate( @Param("id") Integer id, @Param("work") WorkStatus workStatus);
    @Query(
            "select f from Offer f where " +
                    "f.expert.email=:email and f.workStatus='SELECTED' or f.workStatus='PAYED'"
    )
    List<Offer> expertHistory(@Param("email") String email);
    @Query(
            "select f from Offer f where " +
                    "f.orders.customers=:email and f.workStatus='SELECTED' or f.workStatus='PAYED'"
    )
    List<Offer> customerHistory(@Param("email") String email);
    List<Offer> findAll(Specification<Offer> spec);
    Offer findByOrdersIdAndWorkStatus(Integer id,WorkStatus workStatus);



}
