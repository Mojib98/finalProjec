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
    Integer id;
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
        System.out.println(service);
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
        List<Orders> list = specialistService.findOrders(specialist);
        for (Orders orders:list){
            System.out.println(orders);
        }
    }
    public void writeOffer(){
        Orders orders = null;
        List<Orders> list = specialistService.findOrders(specialist);
        list.stream().forEach(System.out::println);
        System.out.println("insert id");
        Integer id=scanner.nextInt();


        orders= list.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst().get();
        System.out.println("insert des");
        String des=scanner.next();
        System.out.println("isnert price");
        Double offerPrice = scanner.nextDouble();
        Offer offer = new Offer(null,null,offerPrice, LocalDateTime.now(),45,orders,specialist);
        specialistService.insert(offer);


    }


}
