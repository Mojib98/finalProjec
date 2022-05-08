package org.project.service.interfaces;

import org.project.entity.Customer;
import org.project.entity.Expert;
import org.project.entity.RequestForConfirmation;

public interface SingUpService {
     void requestForSingUp(Expert expert);
     void insertCustomer(Customer customer);
}
