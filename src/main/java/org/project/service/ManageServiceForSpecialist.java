package org.project.service;

import org.project.entity.Customer;
import org.project.entity.Specialist;

import java.util.List;
import java.util.Properties;

public interface ManageServiceForSpecialist extends GenericService<Specialist> {
    void changeStatus(Properties properties);
    void handleRequest(List<Specialist> specialists);
    void deleteFromRequestList(List<Specialist> specialists);
    void acceptSpecial();
    List<Properties> search(Properties properties);
    List<Properties> RequestList();

}
