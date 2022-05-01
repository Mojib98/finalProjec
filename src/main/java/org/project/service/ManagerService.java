package org.project.service;

import org.project.entity.BaseClass;
import org.project.entity.Customer;
import org.project.entity.Specialist;


import java.util.List;
import java.util.Properties;

public interface ManagerService extends GenericService<BaseClass> {
    void changeStatus(Customer customer);
    void changeStatus(Properties properties);
    void handleRequest(List<Specialist> specialists);
    void insertService(String name);
    void insertSpecial(String name);
    void ChangePriceService(Double startPrice,Double endPrice);
    void ChangePriceSpecial(Double startPrice,Double endPrice);
    List<BaseClass> showAllService();
    List<Customer> search(Customer customer);
    List<Specialist> search(Specialist specialist);

}
