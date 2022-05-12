package com.finalProject.Project.app;

import com.finalProject.Project.app.impl.SingUpAppImpl;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;
@Component
public class Menu {
    Scanner scanner = new Scanner(System.in);

    public Menu(SingUpAppImpl sing, ManageForSystem manageForSystem, ManagerForExpert managerForExpert) {
        this.sing = sing;
        this.manageForSystem = manageForSystem;
        this.managerForExpert = managerForExpert;
    }

    SingUpAppImpl sing;
    ManageForSystem manageForSystem;
    ManagerForExpert managerForExpert;
    ExpertApp expertApp;
    CustomerApp customerApp;
    public void singUp() throws IOException {
        System.out.println("\t\tWelcome\n\t\t" +
                "for request for specialist insert 'new specialist \n" +
                "\t\tif Customer insert 'customer'");
        String choice = scanner.next();
        switch (choice){
            case "new":
                sing.requestForSingUp();
                break;
            case "customer":
                sing.singUpForCustomer();
                break;

        }

    }
    public void MangeSpecialist(){
        System.out.println("\t\tWelcome" +
                "\n\t\tfor see new request for singUp insert 'newsing'" +
                "\n\t\tfor see new request for specialty insert 'check'");
        String choice = scanner.next().toLowerCase(Locale.ROOT);
        switch (choice){
            case "newsing":
                managerForExpert.determineSingUp();
                break;
            case "check":
                managerForExpert.determineForRequestSpecialty();
                break;
            case "search":
        }
    }
    public void specialistMenu(){
        System.out.println("\t\twelcome" +
                "\n\t\tfor see new order insert 'order'" +
                "\n\t\tfor see your offer insert 'offer'" +
                "\n\t\tfor request for new speciali insert 's'" +
                "\n\t\tfor see your budget insert " +
                "\n\t\tfor see your comment insert ");
        String choice = scanner.next().toLowerCase(Locale.ROOT);
        switch (choice){
            case "order":
                expertApp.seeOrders();
                break;
            case "check":
                expertApp.writeOffer();
                break;
            case "s":
                expertApp.requestForSpecialty();
                break;
            case "budget":
                break;
            case "comment":
            case "start":
            case "down":

        }
    }
    public void mangeManagerSystem(){
        System.out.println("\t\twelcome\n" +
                "\t\tfor insert service 'service'" +
                "\nt\t\tfor insert subService 'sub'" +
                "\n\t\tfor see all specialty insert 's'" +
                "\n\t\tfor see all categury insert 'c'" +
                "\b\t\tfor search insert 'search'");
        String choice = scanner.next().toLowerCase(Locale.ROOT);
        switch (choice){
            case "service":
                manageForSystem.addService();
                break;
            case "sub":
                manageForSystem.addSubService();
                break;
            case "search":
            case "s":
                break;
            case "c":
                manageForSystem.showListOfService();
        }
    }
    public void customerMenu(){
        System.out.println("\t\tWelcome" +
                "\n\t\tfor see new request for singUp insert 'newsing'" +
                "\n\t\tfor see new request for specialty insert 'check'");
        String choice = scanner.next().toLowerCase(Locale.ROOT);
        switch (choice){
            case "order":
                customerApp.createOrder();
                break;
            case "offer":
                customerApp.seeMyOrder();
                break;
            case "search":
                customerApp.choiceOffer();
            case "change":
        }
    }
}
