package org.project.app;

import org.project.entity.Customer;
import org.project.entity.Service;
import org.project.entity.Specialist;
import org.project.entity.enumeration.UserStatus;
import org.project.service.imp.ServiceForServiceImpl;

import java.util.List;
import java.util.Scanner;

public class ManageForSystem {
    Scanner scanner = new Scanner(System.in);
    List<Service> serviceList;
    ServiceForServiceImpl serviceForService = new ServiceForServiceImpl();
    public void addService(){
        System.out.println("enter name");
        String name = scanner.next();
        System.out.println("enter price");
        Double pl=scanner.nextDouble();
        System.out.println("enter prU");
        Double pu=scanner.nextDouble();
        System.out.println("enter name");
        Service service = new Service(null,null,name,pu,pl,null);
        serviceForService.addService(service);

    }
    public void addSpecialty(){
        System.out.println("enter name of category");
        String name = scanner.next();
        Service service = serviceForService.findByName(name);
        System.out.println("Enter name ");
        String name1 = scanner.next();
        System.out.println("Enter pu ");
        Double pu = scanner.nextDouble();
        System.out.println("Enter pl ");
        Double pl = scanner.nextDouble();
        Service service1 = new Service(null,null,name1,pl,pu,service);
        serviceForService.insertSpecialty(service1);

    }
    public void showListOfSpecialty(){
        List<Service> serviceList = serviceForService.showAllSpecialty();
        for (Service service:serviceList){
            System.out.println(service.getName());
        }
    }
    public void showListOfService(){
         serviceList = serviceForService.findJustCategory();
        for (Service service:serviceList){
            System.out.println(service.getName());
        }
    }
    public void search(){
        Customer customer = optionForSearch();
        System.out.println(customer);
        var list= serviceForService.search(customer);
        list.forEach(System.out::println);

    }
    private Customer optionForSearch(){
        System.out.println("\t\t!!!if want add option insert request else insert  'no'");
        System.out.println("\tfirst name");
        String fName = checker();
        System.out.println("\tlast name");
        String lName = checker();
        System.out.println("\temail");
        String email = checker();
        System.out.println("\tstatus");
        String status =checker();
        UserStatus status1 = UserStatus.valueOf(status);
//        UserStatus status1 = UserStatus.CONFIRMED;

        return new Customer(null,null,fName,lName,email,null,status1);

    }
    private String checker(){
        String string = scanner.next();
        if (string.equals("no"))
            return null;
        else
            return string;

    }
}
