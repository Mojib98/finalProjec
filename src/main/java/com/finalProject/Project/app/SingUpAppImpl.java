package com.finalProject.Project.app;

import com.finalProject.Project.entity.Avatar;
import com.finalProject.Project.entity.Customer;
import com.finalProject.Project.entity.Expert;
import com.finalProject.Project.entity.enumeration.UserStatus;
import com.finalProject.Project.service.interfaces.SingUpService;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Scanner;
@Component
public class SingUpAppImpl {
    Scanner scanner = new Scanner(System.in);
    SingUpService sing;

    public SingUpAppImpl(SingUpService sing) {
        this.sing = sing;
    }

    private final Utility utility = new Utility();


    public void requestForSingUp() throws IOException {
        //همه این ورودی و خروجی رو تبدیل به تابع بکن با چک کردن ورودی
        String fname = utility.setName();
        String lname = utility.setName();
        String email = utility.email();
        String password = utility.setPassword();
        System.out.print("please insert about u");
        String about = scanner.next();
        System.out.println();
        InputStream image = utility.pathOfPicture();
        Avatar avatar = new Avatar(image.readAllBytes());
        Expert expert = new Expert(null,null,fname,lname,email,password,null,avatar);
        sing.requestForSingUp(expert);
    }

    public void singUpForCustomer() {
        String fName = utility.setName();
        String lName = utility.setName();
        String email = utility.email();
        String password = utility.setPassword();
        Customer customer = new Customer(null, null,
                fName, lName, email, password, UserStatus.ACTIVE);
        sing.insertCustomer(customer);
    }

}
