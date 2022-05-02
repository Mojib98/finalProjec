package org.project.app;

import org.project.entity.RequestForNewSpecialization;
import org.project.entity.Service;
import org.project.entity.Specialist;
import org.project.entity.enumeration.Statuses;
import org.project.service.imp.ServiceForServiceImpl;
import org.project.service.imp.SpecialistService;

import java.util.List;
import java.util.Scanner;

public class SpecialistApp {
    Specialist specialist;
    List<Service> serviceList;
    Scanner scanner = new Scanner(System.in);
    ServiceForServiceImpl service = new ServiceForServiceImpl();
    SpecialistService specialistService = new SpecialistService();
    public void showListOfSpecialty(){
        this.serviceList = service.showAllSpecialty();
        for (Service service:serviceList){
            System.out.println(service.getName());
        }
    }
    public void requestForSpecialty(){
        showListOfSpecialty();
        System.out.println("select on by name");
        String serviceName=scanner.next();
        Service service = serviceList.stream()
                .filter(p -> p.getName().equals(serviceName))
                .findFirst().get();
        String name = scanner.next();
        String describe = scanner.next();
        RequestForNewSpecialization request = new RequestForNewSpecialization(name,
                describe, Statuses.AWAITING_CONFIRMATION,
                specialist,service);
        specialistService.insert(request);
    }


}
