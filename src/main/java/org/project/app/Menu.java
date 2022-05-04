package org.project.app;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    SingUpApp sing = new SingUpApp();
    ManagerForSpecialist managerForSpecialist = new ManagerForSpecialist();
    ManageForSystem manageForSystem = new ManageForSystem();
    public void singUp() throws IOException {
        System.out.println("\t\tWelcome\n\t\t" +
                "for request for specialist insert 'new specialist \n" +
                "\t\tfor tracking your request insert 'track\n" +
                "\t\tif Customer insert 'customer'");
        String choice = scanner.next();
        switch (choice){
            case "new specialist":
                sing.requestForSingUp();
                break;
            case "customer":
                sing.singUpForCustomer();
                break;
            case "track":
                sing.tracking();
            default:
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
                managerForSpecialist.determineSingUp();
                break;
            case "check":
                managerForSpecialist.determineForRequestSpecialty();
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
                manageForSystem.addSpecialty();
                break;
            case "search":
            case "s":
                manageForSystem.showListOfSpecialty();
                break;
            case "c":
                manageForSystem.showListOfService();
        }
    }
}
