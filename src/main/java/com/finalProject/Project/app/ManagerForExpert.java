package com.finalProject.Project.app;

import com.finalProject.Project.entity.Expert;
import com.finalProject.Project.entity.Service;
import com.finalProject.Project.entity.Specialty;
import com.finalProject.Project.entity.enumeration.UserStatus;
import com.finalProject.Project.service.imp.ManageExpertService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
@Component
public class ManagerForExpert {
    Scanner scanner = new Scanner(System.in);
    ManageExpertService manager;

    public ManagerForExpert(ManageExpertService manager) {
        this.manager = manager;
    }

    public void determineSingUp(){
        List<Expert> list = manager.requestListSingUp();
        List<Expert> accept = new ArrayList<>();
        List<Expert> unAccept = new ArrayList<>();
        for(Expert request:list){
            System.out.print(request.getId()+"  ");
            System.out.print(request.getFirstName()+"  ");
            System.out.print(request.getLastName()+"  ");
            System.out.print(request.getTime()+"  ");
            System.out.println("if confirmation insert 'y' or insert 'n'");
            char check=scanner.next().charAt(0);
            switch (check){
                case 'y':
                    accept.add(request);
                    break;
                case 'n':
                    unAccept.add(request);
                    break;
                default:
            }
        }
        manager.handleRequestForExpert(accept,unAccept);
    }
    public void determineForRequestSpecialty() {
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
     manager.handelRequestForSpecialty(accept,unAccept);
    }
    public void search(){
        Expert specialist = optionForSearch();
        System.out.println(specialist);

    }
    private Expert optionForSearch(){
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

return null;
    }
    private String checker(){
        String string = scanner.next();
        if (string.equals("no"))
            return null;
        else
            return string;

    }
    public void removeExpert() {
        List<Expert> list = manager.requestListSingUp();
        for (Expert request : list) {
            System.out.print(request.getId() + "  ");
            System.out.print(request.getFirstName() + "  ");
            System.out.print(request.getLastName() + "  ");
            System.out.print(request.getTime() + "  ");
        }
        System.out.println("please insert number id");
        Integer id = scanner.nextInt();
        Expert expert = list.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst().get();
        manager.remove(expert);

    }




}
