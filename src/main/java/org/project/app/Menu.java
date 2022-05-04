package org.project.app;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    SingUpApp sing = new SingUpApp();
    ManagerForSpecialist managerForSpecialist = new ManagerForSpecialist();
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
}
