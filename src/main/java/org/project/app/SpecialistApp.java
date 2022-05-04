package org.project.app;

import lombok.Setter;
import org.project.entity.*;
import org.project.entity.enumeration.Statuses;
import org.project.service.imp.ServiceForServiceImpl;
import org.project.service.imp.SpecialistService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
@Setter
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
      /*  RequestForNewSpecialization request = new RequestForNewSpecialization(name,
                describe, Statuses.AWAITING_CONFIRMATION,
                specialist,service);*/
        RequestForNewSpecialization request = new RequestForNewSpecialization(null,null,name,
                describe, Statuses.AWAITING_CONFIRMATION,
                specialist,service);
        specialistService.insert(request);
    }
    public void seeOrders(){
        List<Orders> list = specialistService.findOrders();
        for (Orders orders:list){
            System.out.println(orders);
        }
    }
    public void writeOffer(){
        Orders orders = null;
        List<Orders> list = specialistService.findOrders();
        orders=list.get(0);
        String des="sfsfdsdf";
        Double offerPrice = 3000D;
        Offer offer = new Offer(null,null,offerPrice, LocalDateTime.now(),45,orders);
        specialistService.insert(offer);


    }


}
