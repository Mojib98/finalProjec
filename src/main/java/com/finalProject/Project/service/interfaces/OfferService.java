package com.finalProject.Project.service.interfaces;

import com.finalProject.Project.entity.Offer;
import com.finalProject.Project.entity.enumeration.WorkStatus;
import com.finalProject.Project.repository.interfaces.OfferRepository;

import java.util.List;

public interface OfferService {
    void insertOffer(Offer offer);
    List<Offer> findMyOffers(Integer id);
    List<Offer> findByOrderId(Integer id);
    List<Offer> findById(Integer id);
    void removeOffer(List<Offer> offers);
    List<Offer> findExpertOfferForAction(Integer id, WorkStatus workStatus);
    Offer findOfferById(Integer id);
    List<Offer> sortByRateAndPrice(Integer id);
    List<Offer> sortByRate(Integer id);
    List<Offer> sortByPrice(Integer id);
}
