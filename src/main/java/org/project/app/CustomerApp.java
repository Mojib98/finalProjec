package org.project.app;

import lombok.Setter;
import org.project.entity.Customer;
import org.project.entity.Order;
import org.project.entity.Service;
import org.project.service.imp.CustomerServiceImplImpl;
import org.project.service.imp.ServiceForServiceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
@Setter
public class CustomerApp {
    Customer customer;
    ServiceForServiceImpl service = new ServiceForServiceImpl();
    CustomerServiceImplImpl customerRepository = new CustomerServiceImplImpl();
    Scanner scanner = new Scanner(System.in);
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
            throw new RuntimeException();
        System.out.println( service1);
        return service1;
    }
    public void seeOffer(){}
    public void choseOffer(){}
    public void createOrder(){
        System.out.println("insert des");
        String describe=scanner.next();
        System.out.println("select specil");
        String sp = scanner.next();
        System.out.println("please insenrt offer price");
        Double p=scanner.nextDouble();
        Service service =findSpecialtyByName(sp);
        Order orders = new Order(null,null,p, LocalDateTime.now(),customer,service);
        customerRepository.insert(orders);
    }
    public void changePassword(){}



}
