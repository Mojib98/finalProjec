package org.project.service.interfaces;

import org.project.entity.Customer;
import org.project.entity.Expert;

public interface SingUpService {
     void requestForSingUp(Expert expert);
     void insertCustomer(Customer customer);
}
