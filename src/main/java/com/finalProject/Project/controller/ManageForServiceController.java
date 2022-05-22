package com.finalProject.Project.controller;

import com.finalProject.Project.entity.Expert;
import com.finalProject.Project.entity.Specialty;
import com.finalProject.Project.entity.enumeration.UserStatus;
import com.finalProject.Project.service.imp.ManageExpertService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@RestController
@RequestMapping("/exp")
public class ManageForServiceController {

    Scanner scanner = new Scanner(System.in);
    ManageExpertService manager;



    public void determineSingUp() {
        try {
            List<Expert> list = manager.requestListSingUp();
            List<Expert> accept = new ArrayList<>();
            List<Expert> unAccept = new ArrayList<>();
            for (Expert request : list) {
                System.out.print(request.getId() + "  ");
                System.out.print(request.getFirstName() + "  ");
                System.out.print(request.getLastName() + "  ");
                System.out.print(request.getTime() + "  ");
                System.out.println("if confirmation insert 'y' or insert 'n'");
                char check = scanner.next().charAt(0);
                switch (check) {
                    case 'y':
                        accept.add(request);
                        break;
                    case 'n':
                        unAccept.add(request);
                        break;
                    default:
                }
            }
            manager.handleRequestForExpert(accept, unAccept);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void determineForRequestSpecialty() {
        try {
            List<Specialty> list = manager.requestListSpecialty();
            List<Specialty> accept = new ArrayList<>();
            List<Specialty> unAccept = new ArrayList<>();
            for (Specialty request : list) {
//            System.out.println(request.getSpecialist());
                System.out.println(request.getService().getName());
                System.out.println(request.getExpert().getFirstName());
                System.out.println("if confirmation insert 'y' or insert 'n'");
                char check = scanner.next().charAt(0);
                switch (check) {
                    case 'y':
                        accept.add(request);
                        break;
                    case 'n':
                        unAccept.add(request);
                        continue;
                    default:
                }
            }
            manager.handelRequestForSpecialty(accept, unAccept);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void search() {
        try {
            Expert expert = optionForSearch();
            List<Expert> experts = manager.search(expert);
            for (Expert expert1 : experts) {
                System.out.println(expert1.getFirstName() + " " +
                        expert1.getLastName() + " " +
                        expert1.getEmail());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Expert optionForSearch() {
        Expert expert = new Expert();
        System.out.println("\t\t!!!if want add option insert request else insert  'no'");
        System.out.println("\tfirst name");
        String fName = checker();
        expert.setFirstName(fName);
        System.out.println("\tlast name");
        String lName = checker();
        expert.setLastName(lName);
        System.out.println("\temail");
        String email = checker();
        expert.setEmail(email);
        System.out.println("\tstatus");
        String status = checker();
        UserStatus status1 = UserStatus.valueOf(status);
        expert.setStatus(status1);
        return expert;
//        UserStatus status1 = UserStatus.CONFIRMED;
    }

    private String checker() {
        String string = scanner.next();
        if (string.equals("no"))
            return null;
        else
            return string;

    }

    public void removeExpert() {
        System.out.println("please insert number id");
        Integer id = scanner.nextInt();
        manager.remove(id);

    }


}
