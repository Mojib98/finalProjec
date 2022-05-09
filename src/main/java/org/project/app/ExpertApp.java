package org.project.app;

import lombok.Setter;
import org.project.entity.*;
import org.project.repository.imp.ExpertRepositoryImpl;
import org.project.service.imp.ExpertServiceImpl;
import org.project.service.imp.ServiceForServiceImpl;
import java.util.List;
import java.util.Scanner;
@Setter
public class ExpertApp {
    Integer id;
    Expert expert;
    List<Service> serviceList;
    Scanner scanner = new Scanner(System.in);
    ExpertServiceImpl expertService = new ExpertServiceImpl();
    ServiceForServiceImpl service  = new ServiceForServiceImpl();
    Utility utility = new Utility();
    public void showListOfSpecialty(){
        this.serviceList = service.findAllService();
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
  /*  public void seeOrders(){
        List<Order> list = specialistService.findOrders(specialist);
        for (Order orders:list){
            System.out.println(orders.getDescribe());
            System.out.println(orders.getOfferPrice());
            System.out.println(orders.getTimeForWork());
            Customer customer = specialistService.findByIdCustomer(orders.getCustomers().getId());
            System.out.println(orders.getCustomers().getId());
        }
    }
    public void writeOffer(){
        Order order = null;
        List<Order> list = expertRepository.findOrders(this.specialist);
        list.stream().forEach(System.out::println);
        System.out.println("insert id");
        Integer id=utility.giveIntegerInput();
        order= list.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst().get();
        System.out.println("insert des");
        String des=scanner.next();
        System.out.println("isnert price");
        Double offerPrice = scanner.nextDouble();
        System.out.println("insert time");
        LocalDateTime dateTime = utility.dateTime();
        if (isTimeBefore(order.getTime(),dateTime)){
            System.out.println("bad time ");
            return;
        }
        Offer offer = new Offer(null,null,offerPrice, dateTime,45,order,specialist);
        specialistService.insert(offer);
        specialistService.changeWorkFlow(order);


    }
    public void changePassword(){}
    private boolean checkPrice(Service service,Double price){
        Double lowerPrice =service.getLowerPrice();
        Double upperPrice = service.getUpperPrice();
        int low = lowerPrice.compareTo(price);//1
        int up = upperPrice.compareTo(price);//-1
        if (low>=up )
            return true;
//        return price >= lowerPrice && price <= upperPrice;
        return false;
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
    public void arriveOffer(){
        List<AcceptOffer> acceptOffers =specialistService.findMyAcceptOffer(3);
        for (AcceptOffer a:acceptOffers){
            System.out.println(a.getId());
            System.out.println(a.getWorkTime());
        }
        System.out.println("please select one of for start ");
        Integer id = scanner.nextInt();
        specialistService.changeWorkBySpecialist(id,WorkStatus.START);
    }
    public void downWork(){
        List<AcceptOffer> acceptOffers =specialistService.findMyAcceptOffer(3);
        for (AcceptOffer a:acceptOffers){
            System.out.println(a.getId());
            System.out.println(a.getWorkTime());
        }
        System.out.println("please select one of for start ");
        Integer id = scanner.nextInt();
        specialistService.changeWorkBySpecialist(id,WorkStatus.DONE);
    }*/

}
