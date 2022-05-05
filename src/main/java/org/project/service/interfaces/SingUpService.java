package org.project.service.interfaces;

import org.project.entity.Customer;
import org.project.entity.RequestForConfirmation;

public interface SingUpService {
     Integer requestForSingUp(RequestForConfirmation request);
     RequestForConfirmation tracking(Integer trackNumber);
     void removeRequest(RequestForConfirmation request);
     void insertCustomer(Customer customer);
}
