package org.project.app;

import org.project.entity.Avatar;
import org.project.entity.Customer;
import org.project.entity.RequestForConfirmation;
import org.project.entity.enumeration.Statuses;
import org.project.service.imp.SingUpService;

import java.io.*;
import java.util.Scanner;

public class SingUpApp {
    Scanner scanner = new Scanner(System.in);
    SingUpService sing = new SingUpService();


    public void requestForSingUp() throws IOException {
        //همه این ورودی و خروجی رو تبدیل به تابع بکن با چک کردن ورودی
        System.out.println("please insert your Firstname");
        String fname = scanner.next();
        System.out.println("please insert your Lastname");
        String lname = scanner.next();
        System.out.println("please insert your email");
        String email = scanner.next();
        System.out.println("please insert your password");
        String password = scanner.next();
        System.out.println("please insert about u");
        String about = scanner.next();
        System.out.println("insert path of pich");
        String path = scanner.next();
        File file = new File(path);
        InputStream object1 = new FileInputStream(file);
        RequestForConfirmation request = new RequestForConfirmation(null,null,fname,lname,email,password,null,about);
        request.setAvatar(new Avatar(object1.readAllBytes()));
       Integer track= sing.requestForSingUp(request);
        System.out.println("your trackNumber is "+track);
    }
    public void tracking(){
        System.out.println("please inseert number");
        Integer track = scanner.nextInt();
        RequestForConfirmation request = sing.tracking(track);
        if (request == null){
            System.out.println("your tracking number not find ");
        }else {
            System.out.println(request);
            if (request.getStatus().equals(Statuses.UNCONFIRMED)) {
                System.out.println("your request in umconfirmed please try again");
                removeRequest(request);
            }else
                System.out.println("your request is "+request.getStatus());
        }
    }
    private void removeRequest(RequestForConfirmation request){
        sing.removeRequest(request);
    }
    public void singUpForCustomer(){
        System.out.println("please insert your Firstname");
        String fname = scanner.next();
        System.out.println("please insert your Lastname");
        String lname = scanner.next();
        System.out.println("please insert your email");
        String email = scanner.next();
        System.out.println("please insert your password");
        String password = scanner.next();
        Customer customer = new Customer(null,null,fname,lname,email,password,Statuses.ACTIVE);
        sing.insertCustomer(customer);
    }

}