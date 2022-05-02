package org.project.app;

import org.project.entity.Service;
import org.project.service.imp.ServiceForServiceImpl;

import java.util.Scanner;

public class ManageForSystem {
    Scanner scanner = new Scanner(System.in);
    ServiceForServiceImpl serviceForService = new ServiceForServiceImpl();
    public void AddService(){
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
}
