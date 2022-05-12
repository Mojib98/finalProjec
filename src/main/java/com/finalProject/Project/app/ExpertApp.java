package com.finalProject.Project.app;

import com.finalProject.Project.entity.*;
import com.finalProject.Project.entity.enumeration.WorkStatus;
import com.finalProject.Project.service.imp.ExpertService;
import com.finalProject.Project.service.imp.ServicesServiceImpl;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
@Setter
@Component
public class ExpertApp {


    Integer id;
    Expert expert;
    List<Service> serviceList;
    Scanner scanner = new Scanner(System.in);

    public ExpertApp(ExpertService expertService, ServicesServiceImpl servicesService) {
        this.expertService = expertService;
        this.servicesService = servicesService;
    }

    ExpertService expertService;
    ServicesServiceImpl servicesService;
    Utility utility = new Utility();
    public void showListOfSpecialty(){
        this.serviceList = servicesService.showAllService();
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
        Specialty specialty = new Specialty(null,null,this.expert,service);
        expertService.requestForSpecialty(specialty);


    }
    public void seeOrders(){
        List<com.finalProject.Project.entity.Order> list = expertService.findOrders(expert);
        for (Order orders:list){
            System.out.print(orders.getDescribe()+" ");
            System.out.print(orders.getOfferPrice()+" ");
            System.out.print(orders.getTimeForWork()+" ");
            System.out.print(orders.getCustomers().getId()+" ");
        }

    }
    public void writeOffer(){
        Order order = null;
        List<Order> list = expertService.findOrders(expert);
        for (Order orders:list){
            System.out.print(orders.getDescribe()+"\t");
            System.out.print(orders.getOfferPrice()+"\t ");
            System.out.print(orders.getTimeForWork()+"\t ");
            System.out.print(orders.getId()+"\t ");
            System.out.println();
        };
        System.out.println("insert id");
        Integer id=utility.giveIntegerInput();
        order= list.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst().get();
        System.out.println("insert des");
        String des=scanner.next();
        System.out.println("isnert price");
        Integer offerPrice = scanner.nextInt();
        if (checkPrice(order.getSubService(),offerPrice))
            return;
        System.out.println("insert time");
        LocalDateTime dateTime = utility.dateTime();
      /*  if (dateTime.isBefore(order.getTime())){
            System.out.println("bad time ");
            return;
        }*/
       Offer offer = new Offer(null,null,offerPrice,dateTime, WorkStatus.WAIT_FOR_CHOICE,expert,order);

        expertService.insertOffer(offer,order);



    }
    public void seeOrderForStart(){
        Order order = null;
        List<Order> list = expertService.findOrders(expert);
        for (Order orders:list){
            System.out.print(orders.getDescribe()+" ");
            System.out.print(orders.getOfferPrice()+" ");
            System.out.print(orders.getTimeForWork()+" ");
            System.out.print(orders.getId()+" ");
        };
        System.out.println("insert id");
        Integer id=utility.giveIntegerInput();
        order= list.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst().get();
    }

    public void changePassword(){}
    private boolean checkPrice(SubService subService,Integer price){

        return price < subService.getBasePrice() ;

    }
    private boolean isTimeBefore(LocalDateTime orderTime,LocalDateTime offerTime){
        if (offerTime.isBefore(orderTime)){
            return true;
        }
        return false;
    }
    private boolean isCurrentTime(LocalDateTime orderTime,LocalDateTime offerTime){
        if (offerTime.equals(orderTime)){
            return true;
        }
        return false;
    }
    public void startWork(){
        List<Offer> acceptOffers =expertService.findOfferForAction(expert.getId(),WorkStatus.WAIT_FOR_ARRIVE);
        for (Offer a:acceptOffers){
            System.out.println(a.getId());
            System.out.println(a.getWorkTime());
        }
        System.out.println("please select one of for start ");
        Integer id = scanner.nextInt();
        expertService.changeWorkByExpert(id,WorkStatus.START);
    }
    public void downWork(){
        List<Offer> acceptOffers =expertService.findOfferForAction(expert.getId(),WorkStatus.START);
        for (Offer a:acceptOffers){
            System.out.println(a.getId());
            System.out.println(a.getWorkTime());
        }
        System.out.println("please select one of for start ");
        Integer id = scanner.nextInt();
        expertService.changeWorkByExpert(id,WorkStatus.DONE);
    }


}
