package org.project.app;

import java.io.IOException;
import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    SingUpApp sing = new SingUpApp();
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
}
