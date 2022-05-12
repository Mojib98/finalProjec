package com.finalProject.Project.service.imp;

import com.finalProject.Project.entity.Customer;
import com.finalProject.Project.entity.Expert;
import com.finalProject.Project.entity.enumeration.UserStatus;
import com.finalProject.Project.repository.interfaces.SingUpRepository;
import com.finalProject.Project.service.interfaces.SingUpService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SingUpServiceImp  implements SingUpService {
   private final SingUpRepository sing;

    public SingUpServiceImp(SingUpRepository sing) {
        this.sing = sing;
    }


    @Override
    public void requestForSingUp(Expert expert) {
        expert.setStatus(UserStatus.AWAITING_CONFIRMATION);
        expert.setBudget(0);
        sing.save(expert);
    }

    @Override
    @Transactional
    public void insertCustomer(Customer customer) {
        customer.setStatus(UserStatus.ACTIVE);
        customer.setBudget(50000);
        sing.save(customer);
    }
}
