package org.project.app;

import org.project.entity.Expert;
import org.project.entity.Specialty;
import org.project.entity.enumeration.UserStatus;
import org.project.service.imp.ManagerServiceForExpertImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManagerForExpert {
    Scanner scanner = new Scanner(System.in);
    ManagerServiceForExpertImpl manager = new ManagerServiceForExpertImpl();
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




}
