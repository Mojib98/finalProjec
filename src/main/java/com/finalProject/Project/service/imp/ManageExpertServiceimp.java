package com.finalProject.Project.service.imp;

import com.finalProject.Project.entity.Customer;
import com.finalProject.Project.entity.Expert;
import com.finalProject.Project.entity.Specialty;
import com.finalProject.Project.entity.dto.SpecialistDto;
import com.finalProject.Project.entity.enumeration.UserStatus;
import com.finalProject.Project.repository.interfaces.CustomerRepository;
import com.finalProject.Project.repository.interfaces.ManageRepositoryForExpert;
import com.finalProject.Project.repository.interfaces.SpecialtyRepository;
import com.finalProject.Project.service.interfaces.ManageServiceForExpert;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ManageExpertServiceimp implements ManageServiceForExpert {
    private final ManageRepositoryForExpert manageRepositoryForExpert;
    private final SpecialtyRepository specialtyRepository;
    private final CustomerRepository customerRepository;

    public ManageExpertServiceimp(ManageRepositoryForExpert manageRepositoryForExpert, SpecialtyRepository specialtyRepository, CustomerRepository customerRepository) {
        this.manageRepositoryForExpert = manageRepositoryForExpert;
        this.specialtyRepository = specialtyRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public void changeStatusExpert(Expert expert) {
        manageRepositoryForExpert.updateWorkStatus(expert.getId(), expert.getStatus());
    }

    @Override
    @Transactional
    public void handleRequestForExpert(List<Integer> accepted) {
        List<Integer> removlaList=accepted;

        for (Integer id : accepted) {
            Expert expert = new Expert();
            expert.setId(id);
            expert.setStatus(UserStatus.CONFIRMED);
            System.out.println(expert);
            changeStatusExpert(expert);
            removlaList.remove(id);
        }
        for (Integer id : removlaList) {
            Expert expert = new Expert();
            expert.setId(id);
            System.out.println(expert);
            expert.setStatus(UserStatus.UNCONFIRMED);
            changeStatusExpert(expert);
        }
    }


    @Override
    @Transactional
    public List<Expert> requestListSingUp() {
        List<Expert> list = manageRepositoryForExpert.findAllByStatus(UserStatus.AWAITING_CONFIRMATION);
        return list;
    }

    @Override
    @Transactional
    public void handelRequestForSpecialty(List<Integer> accepted) {
        for (Integer id : accepted) {
            Specialty specialty = specialtyRepository.findById(id).get();
            specialty.setId(id);
            specialty.setStatus(UserStatus.CONFIRMED);
            specialtyRepository.save(specialty);
        }
 /*       for (Specialty unAccept : unAccepted) {
            unAccept.setStatus(UserStatus.UNCONFIRMED);
            specialtyRepository.save(unAccept);
            specialtyRepository.delete(unAccept);
            removeSpecialty(unAccept);


        }*/
    }

    @Override
    @Transactional
    public void removeSpecialty(Specialty specialty) {
        specialtyRepository.delete(specialty);
    }

    public List<Specialty> requestListSpecialty() {
        List<Specialty> list = null;
        list = specialtyRepository.find(UserStatus.AWAITING_CONFIRMATION);
        for (Specialty specialty:list){
            System.out.println(specialty.getService().getName());
            System.out.println(specialty.getExpert().getFirstName());
        }
        return list;
    }

    @Transactional
    public void remove(Integer id) {
        Expert expert = new Expert();
        expert.setId(id);
        manageRepositoryForExpert.delete(expert);
    }

    public void update(Expert expert) {

    }

    public void insert(Expert expert) {
        manageRepositoryForExpert.save(expert);
    }

    public Expert findById(Integer id) {
        return manageRepositoryForExpert.findById(id).get();

    }

    public Specification<Expert> option(Expert expert) {
        Predicate predicate;
        Specification<Expert> specification = new Specification<Expert>() {
            @Override
            public Predicate toPredicate(Root<Expert> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                var criteriaQuery = criteriaBuilder.createQuery(Expert.class);
                List<Predicate> predicates = new ArrayList<>();
                if (expert.getFirstName() != null && !expert.getFirstName().isEmpty())
                    predicates.add(criteriaBuilder.equal(root.get("firstName"), expert.getFirstName()));
                if (expert.getLastName() != null && !expert.getLastName().isEmpty())
                    predicates.add(criteriaBuilder.equal(root.get("lastName"), expert.getLastName()));
                if (expert.getEmail() != null && !expert.getEmail().isEmpty())
                    predicates.add(criteriaBuilder.equal(root.get("email"), expert.getEmail()));
                if (expert.getStatus() != null)
                    predicates.add(criteriaBuilder.equal(root.get("status"), expert.getStatus()));
                if (expert.getRate() != null)
                    predicates.add(criteriaBuilder.equal(root.get("rate"), expert.getRate()));
                criteriaQuery
                        .where(predicates.toArray(new Predicate[0]));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

            }

        };


        return specification;
    }

    public List<Expert> search(Expert expert) {
        Specification<Expert> specification = option(expert);
        return manageRepositoryForExpert.findAll(specification);
    }
    @Transactional
    public void insertSpecialty(SpecialistDto specialistDto){
        Specialty specialty = new Specialty();
        com.finalProject.Project.entity.Service service = new com.finalProject.Project.entity.Service();
        service.setId(specialistDto.getServiceId());
        Expert expert = new Expert();
        expert.setId(specialistDto.getExpertId());
        specialty.setService(service);
        specialty.setExpert(expert);
        specialty.setStatus(UserStatus.CONFIRMED);
        specialtyRepository.save(specialty);

    }
    public List<Customer> searchCustomer(Customer customer) {
        Specification<Customer> specification = optionCustomer(customer);
        return customerRepository.findAll(specification);
    }
    public Specification<Customer> optionCustomer(Customer customer) {
        Predicate predicate;
        Specification<Customer> specification = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                var criteriaQuery = criteriaBuilder.createQuery(Customer.class);
                List<Predicate> predicates = new ArrayList<>();
                if (customer.getFirstName() != null && !customer.getFirstName().isEmpty())
                    predicates.add(criteriaBuilder.equal(root.get("firstName"), customer.getFirstName()));
                if (customer.getLastName() != null && !customer.getLastName().isEmpty())
                    predicates.add(criteriaBuilder.equal(root.get("lastName"), customer.getLastName()));
                if (customer.getEmail() != null && !customer.getEmail().isEmpty())
                    predicates.add(criteriaBuilder.equal(root.get("email"), customer.getEmail()));
                if (customer.getStatus() != null)
                    predicates.add(criteriaBuilder.equal(root.get("status"), customer.getStatus()));
//                if (customer.() != null)
//                    predicates.add(criteriaBuilder.equal(root.get("rate"), customer.getRate()));
                criteriaQuery
                        .where(predicates.toArray(new Predicate[0]));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

            }

        };


        return specification;
    }






}

