package com.finalProject.Project.app;

import com.finalProject.Project.entity.*;
import com.finalProject.Project.service.imp.CustomerServiceImpl;
import com.finalProject.Project.service.imp.OfferServiceImpl;
import com.finalProject.Project.service.imp.ServicesServiceImpl;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
@Setter
@Component
public class CustomerAppImpl {
    @Autowired
    CustomerServiceImpl service;
   private Customer customer;
   private Scanner scanner = new Scanner(System.in);
   private Utility utility = new Utility();
/*    public void seeCategory(){
       for (Service service : list )
           System.out.println(list);
    }


  public void seeSpecialtyByCategory(){
        String name = scanner.next();
        List<Service> list= service.findByCategory(name);
        for (Service service : list )
            System.out.println(list);
    }*/
/*    public Service findSpecialtyByName(String name){
        Service service1=service.findServiceByName(name);
        if (service1==null)
            throw new RuntimeException("your service not find");
        System.out.println( service1);
        return service1;
    }*/

    public void createOrder() {
        System.out.println("insert des");
        String describe = scanner.next();
        System.out.println("adress");
        String address = scanner.next();

        List<SubService> services = service.allSubService();
        for (SubService service : services) {
            System.out.print(service.getName() + "  ");
            System.out.print(service.getBasePrice());
        }
        System.out.println("insert name service for order");
        String nameService = scanner.next();
        SubService subService = services.stream()
                .filter(p -> p.getName().equals(nameService))
                .findFirst().get();
        System.out.println("please insert offer price");
        Integer price = scanner.nextInt();
        checkPrice(subService, price);
        LocalDateTime time = utility.dateTime();
        com.finalProject.Project.entity.Order order = new com.finalProject.Project.entity.Order(null, null, price, time, address, describe, customer, subService);
        service.insertOrder(order);


    }

    private void checkPrice(SubService service, Integer price) {
        if (service.getBasePrice() >= price)
            throw new RuntimeException("bad price");
    }

    public void changePassword() {
        String newPassword = utility.setPassword();

    }

    public void seeMyOrder() {
        List<Order> order = service.findMyOrder(customer.getId());
        for (com.finalProject.Project.entity.Order orders1 : order) {
            System.out.println(orders1);
        }

    }

    public void choiceOffer() {
        List<Order> order = service.findMyOrder(customer.getId());
        for (com.finalProject.Project.entity.Order orders1 : order) {
            System.out.println(orders1);
            Integer id = scanner.nextInt();
            List<Offer> offers = service.findOfferByOrderId(id);
            offers.stream().forEach(System.out::println);
            Integer idOffer = scanner.nextInt();
            Offer offer = offers.stream()
                    .filter(p -> p.getId().equals(idOffer))
                    .findFirst().get();
            Order order1 = order.stream()
                    .filter(p -> p.getId().equals(id))
                    .findFirst().get();
            offers.remove(offer);
            service.choiceOffer(order1,offer,offers);


     /*   Orders orders =customerServiceImpl.findOrderById(id);
        orders.setAcceptOffer(offer);
        orders.setWorkStatus(WorkStatus.WAIT_FOR_ARRIVE);
        customerServiceImpl.choiceOffer(offers,orders);*/
        }
    }
    public void payForOrder(){
        List<Order> acceptOffer = service.myDownOrder(customer.getId());
        Integer orderId=scanner.nextInt();
        Order order = acceptOffer.stream()
                .filter(p -> p.getId().equals(orderId))
                .findFirst().get();
        service.paying(order);

    }
    public void addComment(Order order){
        List<Order> acceptOffer = service.myDownOrder(customer.getId());
        Integer orderId=scanner.nextInt();
        String comment = scanner.nextLine();
        Comment comment1  = new Comment(null,null,comment,this.customer);
       Offer offer = order.getOffer();

        service.paying(order);
    }
    public void sortOfferByPrice(){

    }

  /*
   public void seeAllSpecialty(){
        List<Service> list=
        for (Service service : list ) {
            System.out.println(service.getName());

        }
    }

 public void seeOffer(){}
    public void choseOffer(){
        Integer id = scanner.nextInt();
        Offer offer = customerServiceImpl.findOfferById(id);
        System.out.println(offer);
        Integer idSpecialist=offer.getSpecialists().getId();
        Integer idOrder = offer.getOrder().getId();
        System.out.println(idSpecialist+" "+idOrder);
   Order order = customerServiceImpl.findOrderById(idOrder);
        System.out.println(order);

        AcceptOffer acceptOffer = new AcceptOffer(null,null);
        acceptOffer.setOfferPrice(offer.getOfferPrice());
        acceptOffer.setWorkTime(offer.getWorkTime());
        customerServiceImpl.choice(offer,acceptOffer);



    }

*/


}


