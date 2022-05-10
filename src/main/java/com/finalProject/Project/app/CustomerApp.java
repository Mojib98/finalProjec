/*
package com.finalProject.Project.app;

import com.finalProject.Project.entity.Customer;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
@Setter
public class CustomerApp {
    Customer customer;
    org.project.service.imp.ServiceForServiceImpl service = new ServiceForServiceImpl();
    CustomerServiceImplImpl customerServiceImpl = new CustomerServiceImplImpl();
    Scanner scanner = new Scanner(System.in);
    Utility utility = new Utility();
*/
/*    public void seeCategory(){
       for (Service service : list )
           System.out.println(list);
    }
  *//*

  */
/*  public void seeSpecialtyByCategory(){
        String name = scanner.next();
        List<Service> list= service.findByCategory(name);
        for (Service service : list )
            System.out.println(list);
    }
    public Service findSpecialtyByName(String name){
        Service service1=service.findServiceByName(name);
        if (service1==null)
            throw new RuntimeException("your service not find");
        System.out.println( service1);
        return service1;
    }*//*

    public void createOrder(){
        System.out.println("insert des");
        String describe=scanner.next();
        System.out.println("adress");
        String address = scanner.next();

        List<SubService> services = service.showAllSubService();
        for (SubService service:services){
            System.out.println(service.getName()+"  ");
            System.out.print(service.getBasePrice());
        }
        System.out.println("insert name service for order");
        String nameService  =scanner.next();
        System.out.println("please insert offer price");
        Integer price=scanner.nextInt();
        LocalDateTime time = utility.dateTime();
        SubService subService = services.stream()
                .filter(p -> p.getName().equals(nameService))
                .findFirst().get();
        checkPrice(subService,price);
        Orders orders = new Orders(null,null,price,time,address,describe,customer,subService);
        customerServiceImpl.insertOrder(orders);






    }
    private void checkPrice(SubService service,Integer price){
        if (service.getBasePrice() >= price)
        throw new RuntimeException("bad price");
    }
    public void changePassword(){
        String newPassword=utility.setPassword();



    }
    public void seeMyOffer(){
        List<Orders> orders =customerServiceImpl.myOrder(customer.getId());
        for (Orders orders1 : orders){
            System.out.println(orders1);
        }
    }
    public void choiceOffer(){
        Integer id=scanner.nextInt();
        List<Offer> offers=customerServiceImpl.offerForOrder(id);
        offers.stream().forEach(System.out::println);
        Integer idOffer = scanner.nextInt();
        Offer offer = offers.stream()
                .filter(p -> p.getId().equals(idOffer))
                .findFirst().get();
        Orders orders =customerServiceImpl.findOrderById(id);
        orders.setAcceptOffer(offer);
        orders.setWorkStatus(WorkStatus.WAIT_FOR_ARRIVE);
        customerServiceImpl.choiceOffer(offers,orders);
    }
 */
/*   public void seeAllSpecialty(){
        List<Service> list=
        for (Service service : list ) {
            System.out.println(service.getName());

        }
    }*//*

   */
/* public void seeOffer(){}
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
    public void payForOrder(){
        AcceptOffer acceptOffer = customerServiceImpl.myAcceptOffer(120);
        System.out.println(acceptOffer.getId());
        customerServiceImpl.paying(acceptOffer,91);
    }
    public void addComment(){
        AcceptOffer acceptOffer1 = customerServiceImpl.myAcceptOffer(86);
        Comment comment = new Comment();
        comment.setComment("sadfdf");
        customerServiceImpl.addComment(acceptOffer1,25,comment);
    }
*//*




}
*/
