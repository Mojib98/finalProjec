package com.finalProject.Project.service.imp;

import com.finalProject.Project.entity.Expert;
import com.finalProject.Project.entity.Specialty;
import com.finalProject.Project.entity.enumeration.UserStatus;
import com.finalProject.Project.repository.interfaces.ManageRepositoryForExpert;
import com.finalProject.Project.repository.interfaces.SpecialtyRepository;
import com.finalProject.Project.service.interfaces.ManageServiceForExpert;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ManageExpertService implements ManageServiceForExpert {
    ManageRepositoryForExpert manageRepositoryForExpert;
    SpecialtyRepository specialtyRepository;

    public ManageExpertService(ManageRepositoryForExpert manageRepositoryForExpert, SpecialtyRepository specialtyRepository) {
        this.manageRepositoryForExpert = manageRepositoryForExpert;
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public void changeStatusExpert(Expert expert) {
        manageRepositoryForExpert.updateWorkStatus(expert.getId(),expert.getStatus());
    }

    @Override
    public void handleRequestForExpert(List<Expert> accepted, List<Expert> unAccepted) {
        for (Expert expert:accepted){
            expert.setStatus(UserStatus.CONFIRMED);
            changeStatusExpert(expert);
        }
        for (Expert expert:unAccepted){
            expert.setStatus(UserStatus.UNCONFIRMED);
            changeStatusExpert(expert);
        }
    }

    @Override
    public List<Expert> search(Expert expert) {
        return null;
    }

    @Override
    public List<Expert> requestListSingUp() {
        List<Expert> list = manageRepositoryForExpert.findAllByStatus(UserStatus.AWAITING_CONFIRMATION);
        return list;
    }

    @Override
    public void handelRequestForSpecialty(List<Specialty> accepted, List<Specialty> unAccepted) {
        for (Specialty accept:accepted){
            accept.setStatus(UserStatus.CONFIRMED);
            specialtyRepository.save(accept);
        }
        for (Specialty unAccept:unAccepted){
            unAccept.setStatus(UserStatus.UNCONFIRMED);
            specialtyRepository.save(unAccept);
            specialtyRepository.delete(unAccept);
//            removeSpecialty(unAccept);


        }
    }

    @Override
    public void removeSpecialty(Specialty specialty) {

    }
    public List<Specialty> requestListSpecialty(){
        List<Specialty> list = null;
        list=specialtyRepository.findAllByStatus(UserStatus.AWAITING_CONFIRMATION);
        return list;
    }
}
