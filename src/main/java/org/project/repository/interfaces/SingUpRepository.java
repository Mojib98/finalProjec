package org.project.repository.interfaces;

import org.project.entity.Customer;
import org.project.entity.Expert;

public interface SingUpRepository {
    void requestForSpecialist(Expert request);
    void insertCustomer(Customer customer);
}
