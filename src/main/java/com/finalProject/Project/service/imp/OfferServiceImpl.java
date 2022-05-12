package com.finalProject.Project.service.imp;

import com.finalProject.Project.entity.Offer;
import com.finalProject.Project.repository.interfaces.OfferRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OfferServiceImpl {
    OfferRepository offerRepository;

    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }
    public void insertOffer(Offer offer){
        if (offer.getExpert() !=null)
            offerRepository.save(offer);
            System.err.println(offerRepository);
    }
    public List<Offer> findMyOffers(Integer id){
        return offerRepository.findAllByOrdersId(id);
    }
    @Transactional
    public List<Offer> findByOrderId(Integer id){
        return offerRepository.findAllByOrdersId(id);
    }
    public Offer findById(Integer id){
        return (Offer) offerRepository.find(id);
    }
    public void removeOffer(List<Offer> offers){
            offerRepository.deleteAll(offers);
    }

}
