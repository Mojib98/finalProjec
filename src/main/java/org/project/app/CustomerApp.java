package org.project.app;

import lombok.Setter;
import org.project.entity.*;
import org.project.entity.enumeration.WorkStatus;
import org.project.service.imp.CustomerServiceImplImpl;
import org.project.service.imp.ServiceForServiceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
@Setter
public class CustomerApp {
    Customer customer;
    ServiceForServiceImpl service = new ServiceForServiceImpl();
    CustomerServiceImplImpl customerServiceImpl = new CustomerServiceImplImpl();
    Scanner scanner = new Scanner(System.in);
    Utility utility = new Utility();
    public void seeCategory(){
       List<Service> list= service.findJustCategory();
       for (Service service : list )
           System.out.println(list);
    }
    public void seeAllSpecialty(){
        List<Service> list= service.showAllSpecialty();
        for (Service service : list ) {
            System.out.println(service.getName());
            System.out.println(service.getCategory());
        }
    }
    public void seeSpecialtyByCategory(){
        String name = scanner.next();
        List<Service> list= service.findByCategory(name);
        for (Service service : list )
            System.out.println(list);
    }
    public Service findSpecialtyByName(String name){
        Service service1=service.findByName(name);
        if (service1==null)
            throw new RuntimeException("your service not find");
        System.out.println( service1);
        return service1;
    }
    public void createOrder(){
        System.out.println("insert des");
        String describe=scanner.next();
        System.out.println("adress");
        String address = scanner.next();
        System.out.println("select specilty");
        String sp = scanner.next();
        System.out.println("please insert offer price");
        Double price=scanner.nextDouble();
        Service service =findSpecialtyByName(sp);
        System.out.println(service.getLowerPrice());
        System.out.println(service.getUpperPrice());
        if (checkPrice(service,price)) {
            LocalDateTime localDateTime = utility.dateTime();
            Order order = new Order(null, null, price, localDateTime, customer, service);
            order.setWorkStatus(WorkStatus.WAIT_FOR_OFFER);
            order.setDescribe(describe);
            order.setAddress(address);
            customerServiceImpl.insert(order);
        }else{
            System.out.println("price is bad try again");
        }
    }
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
    public void changePassword(){
        String newPassword=utility.setPassword();



    }
    public void seeOffer(){}
    public void choseOffer(){
        Integer id = scanner.nextInt();
        Offer offer = customerServiceImpl.findOfferById(id);
        System.out.println(offer);
        Integer idSpecialist=offer.getSpecialists().getId();
        Integer idOrder = offer.getOrder().getId();
        System.out.println(idSpecialist+" "+idOrder);
     /*   Order order = customerServiceImpl.findOrderById(idOrder);
        System.out.println(order);*/
        AcceptOffer acceptOffer = new AcceptOffer(null,null);
        acceptOffer.setOfferPrice(offer.getOfferPrice());
        acceptOffer.setWorkTime(offer.getWorkTime());
        customerServiceImpl.choice(offer,acceptOffer);



    }
    public void payForOrder(){
        AcceptOffer acceptOffer = customerServiceImpl.myAcceptOffer(86);
        System.out.println(acceptOffer.getId());
        customerServiceImpl.paying(acceptOffer,25);
    }
    public void addComment(){
        AcceptOffer acceptOffer1 = customerServiceImpl.myAcceptOffer(86);
        Comment comment = new Comment();
        comment.setComment("sadfdf");
        customerServiceImpl.addComment(acceptOffer1,25,comment);
    }




}
