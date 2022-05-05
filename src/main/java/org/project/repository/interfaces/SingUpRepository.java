package org.project.repository.interfaces;

import org.project.entity.Customer;
import org.project.entity.RequestForConfirmation;

public interface SingUpRepository {
     void removeRequest(RequestForConfirmation request);
    void requestForSpecialist(RequestForConfirmation request);
    RequestForConfirmation findByTrackingNumber(Integer track);
    void insertCustomer(Customer customer);
}
