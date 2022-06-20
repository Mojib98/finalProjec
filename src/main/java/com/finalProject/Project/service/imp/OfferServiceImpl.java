package com.finalProject.Project.service.imp;

import com.finalProject.Project.entity.Offer;
import com.finalProject.Project.entity.enumeration.WorkStatus;
import com.finalProject.Project.repository.interfaces.OfferRepository;
import com.finalProject.Project.service.interfaces.OfferService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {
    private OfferRepository offerRepository;

    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Transactional
    public void insertOffer(Offer offer) {
//        if (offer.getExpert() !=null)
        offer.setWorkStatus(WorkStatus.WAIT);
        offerRepository.save(offer);
        System.err.println(offerRepository);
    }

    @Transactional
    public List<Offer> findMyOffers(Integer id) {
        return offerRepository.findAllByOrdersId(id);
    }

    @Transactional
    public List<Offer> findByOrderId(Integer id) {
        return offerRepository.findAllByOrdersId(id);
    }

    @Transactional
    public List<Offer> findById(Integer id) {
        return offerRepository.find(id);
    }

    @Transactional
    public void removeOffer(List<Offer> offers) {
        offerRepository.deleteAll(offers);
    }

    @Transactional
    public List<Offer> findExpertOfferForAction(Integer id, WorkStatus workStatus) {
        return offerRepository.findListOffer(id, workStatus);
    }

    @Transactional
    public Offer findOfferById(Integer id) {
        return offerRepository.findById(id).get();
    }

    @Transactional
    public List<Offer> sortByRateAndPrice(Integer id) {
        return offerRepository.sortByPriceAndRate(id, WorkStatus.WAIT);

    }

    @Transactional
    public List<Offer> sortByRate(Integer id) {
        return offerRepository.sortByRate(id, WorkStatus.WAIT);

    }

    @Transactional
    public List<Offer> sortByPrice(Integer id) {
        return offerRepository.sortByPrice(id, WorkStatus.WAIT);

    }
    @Transactional
    public Offer findOfferByOrderId(Integer id){
        return offerRepository.findByOrdersIdAndWorkStatus(id,WorkStatus.PAYED);
    }

}
