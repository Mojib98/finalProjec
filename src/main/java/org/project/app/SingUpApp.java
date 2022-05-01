package org.project.app;

import org.project.entity.RequestForConfirmation;
import org.project.entity.enumeration.Statuses;
import org.project.service.imp.SingUpService;

import java.util.Scanner;

public class SingUpApp {
    Scanner scanner = new Scanner(System.in);
    SingUpService sing = new SingUpService();


    public void requestForSingUp() {
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
        RequestForConfirmation request = new RequestForConfirmation(fname,lname,email,password,about);
       Integer track= sing.requestForSingUp(request);
        System.out.println("yout trackNumber is "+track);
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


            }
        }
    }
    private void removeRequest(RequestForConfirmation request){
        sing.removeRequest(request);
    }

}