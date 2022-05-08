package org.project.repository.interfaces;

import org.project.entity.Customer;
import org.project.entity.Expert;
import org.project.entity.RequestForConfirmation;

public interface SingUpRepository {
    void requestForSpecialist(Expert request);
    void insertCustomer(Customer customer);
}
