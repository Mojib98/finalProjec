package com.finalProject.Project.service.imp;

import com.finalProject.Project.entity.Expert;
import com.finalProject.Project.entity.Specialty;
import com.finalProject.Project.entity.enumeration.UserStatus;
import com.finalProject.Project.repository.interfaces.ManageRepositoryForExpert;
import com.finalProject.Project.repository.interfaces.SpecialtyRepository;
import com.finalProject.Project.service.interfaces.ManageServiceForExpert;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ManageExpertService implements ManageServiceForExpert {
    ManageRepositoryForExpert manageRepositoryForExpert;
    SpecialtyRepository specialtyRepository;
    EntityManager entityManager;

    public ManageExpertService(ManageRepositoryForExpert manageRepositoryForExpert, SpecialtyRepository specialtyRepository) {
        this.manageRepositoryForExpert = manageRepositoryForExpert;
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    @Transactional
    public void changeStatusExpert(Expert expert) {
        manageRepositoryForExpert.updateWorkStatus(expert.getId(), expert.getStatus());
    }

    @Override
    @Transactional
    public void handleRequestForExpert(List<Expert> accepted, List<Expert> unAccepted) {
        for (Expert expert : accepted) {
            expert.setStatus(UserStatus.CONFIRMED);
            System.out.println(expert);
            changeStatusExpert(expert);
        }
        for (Expert expert : unAccepted) {
            expert.setStatus(UserStatus.UNCONFIRMED);
            changeStatusExpert(expert);
        }
    }

    @Override
    public List<Expert> search(Expert expert) {
        return null;
    }

    @Override
    @Transactional
    public List<Expert> requestListSingUp() {
        List<Expert> list = manageRepositoryForExpert.findAllByStatus(UserStatus.AWAITING_CONFIRMATION);
        return list;
    }

    @Override
    @Transactional
    public void handelRequestForSpecialty(List<Specialty> accepted, List<Specialty> unAccepted) {
        for (Specialty accept : accepted) {
            accept.setStatus(UserStatus.CONFIRMED);
            specialtyRepository.save(accept);
        }
        for (Specialty unAccept : unAccepted) {
            unAccept.setStatus(UserStatus.UNCONFIRMED);
            specialtyRepository.save(unAccept);
            specialtyRepository.delete(unAccept);
            removeSpecialty(unAccept);


        }
    }

    @Override
    @Transactional
    public void removeSpecialty(Specialty specialty) {

    }

    public List<Specialty> requestListSpecialty() {
        List<Specialty> list = null;
        list = specialtyRepository.findAllByStatus(UserStatus.AWAITING_CONFIRMATION);
        return list;
    }

    @Transactional
    public void remove(Expert expert) {
        manageRepositoryForExpert.delete(expert);
    }

    public void update(Expert expert) {

    }

    public void insert(Expert expert) {
        manageRepositoryForExpert.save(expert);
    }

    public Expert findById(Integer id) {
//        return manageRepositoryForExpert.findById(id);
        return null;
    }
    public Specification<Expert> searching(Expert expert){
        Predicate predicate;
        Specification<Expert> specification = new Specification<Expert>() {
            @Override
            public Predicate toPredicate(Root<Expert> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//                var session = sessionFactory.getCurrentSession();
//                var criteriaBuilder = entityManager.getCriteriaBuilder();
                var criteriaQuery = criteriaBuilder.createQuery(Expert.class);
//                var root = criteriaQuery.from(Expert.class); // select query
                List<Predicate> predicates = new ArrayList<>();
                if (expert.getFirstName() != null && !expert.getFirstName().isEmpty())
                    predicates.add(criteriaBuilder.equal(root.get("firstName"), expert.getFirstName()));
                if (expert.getLastName() != null && !expert.getLastName().isEmpty())
                    predicates.add(criteriaBuilder.equal(root.get("lastName"), expert.getLastName()));
                if (expert.getEmail() != null && !expert.getEmail().isEmpty())
                    predicates.add(criteriaBuilder.equal(root.get("email"), expert.getEmail()));
                if (expert.getStatus() != null)
                    predicates.add(criteriaBuilder.equal(root.get("status"), expert.getStatus()));
                criteriaQuery
                        .where(predicates.toArray(new Predicate[0]));
//                return criteriaBuilder.conjunction();
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
//                return predicates;
//                return criteriaQuery;//                return criteriaQuery;

//                return null;
//                return null;
            }

        };



        return specification;
    }
    public List<Expert> searchs(Expert expert){
        Specification<Expert> specification = searching(expert);
        var s= manageRepositoryForExpert.findAll(specification);
        return s;
    }

}

