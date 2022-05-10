package com.finalProject.Project.service.interfaces;

import com.finalProject.Project.entity.Customer;
import com.finalProject.Project.entity.Expert;

public interface SingUpService {
     void requestForSingUp(Expert expert);
     void insertCustomer(Customer customer);
}
