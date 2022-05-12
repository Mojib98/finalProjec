package com.finalProject.Project.app;

import com.finalProject.Project.entity.Customer;
import com.finalProject.Project.entity.Service;
import com.finalProject.Project.entity.SubService;
import com.finalProject.Project.entity.enumeration.UserStatus;
import com.finalProject.Project.service.imp.ServicesServiceImpl;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class ManageForSystem {
   private Scanner scanner = new Scanner(System.in);
    public ServicesServiceImpl serviceForService;

    public ManageForSystem(ServicesServiceImpl serviceForService) {
        this.serviceForService = serviceForService;
    }

    List<Service> serviceList;

    public void addService() {
        System.out.println("enter name");
        String name = scanner.next();
        Service service = new Service(null, null, name);
        serviceForService.insertService(service);


    }

    public void addSubService() {

        List<Service> serviceList;
        serviceList = serviceForService.showAllService();
        for (Service service : serviceList) {
            System.out.println(service.getId());
            System.out.println(service.getName());
        }
        System.out.println("enter name of category");
        String serviceName = scanner.next();
        Service service = serviceList.stream()
                .filter(p -> p.getName().equals(serviceName))
                .findFirst().get();
        System.out.println(service);
        System.out.println("Enter name ");
        String name = scanner.next();
        System.out.println("Enter Base price ");
        Integer price = scanner.nextInt();
        System.out.println("please insert describer");
        String des = scanner.nextLine();
        SubService subService = new SubService(null, null, name, price, des, service);
        System.out.println(subService);
        serviceForService.insertSubService(subService);

    }

    public void showListOfService() {
        List<Service> serviceList;
        serviceList = serviceForService.showAllService();
        for (Service service : serviceList) {
            System.out.println(service.getName());
        }
    }
    public void showListOfSubService(){
         List<SubService> subService = serviceForService.showAllSubService();
        for (SubService service:subService){
            System.out.println(service.getName());
        }
    }
    public void search(){
        Customer customer = optionForSearch();
        System.out.println(customer);
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
