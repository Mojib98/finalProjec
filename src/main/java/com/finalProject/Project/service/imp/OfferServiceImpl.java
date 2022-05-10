package com.finalProject.Project.service.imp;

import com.finalProject.Project.entity.Offer;
import com.finalProject.Project.repository.interfaces.OfferRepository;
import org.springframework.stereotype.Service;

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
}
