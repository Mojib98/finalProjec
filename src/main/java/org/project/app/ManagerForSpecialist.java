package org.project.app;

import org.project.entity.RequestForConfirmation;
import org.project.entity.RequestForNewSpecialization;
import org.project.entity.Specialist;
import org.project.entity.enumeration.UserStatus;
import org.project.service.interfaces.ManageServiceForSpecialist;
import org.project.service.imp.ManagerServiceForSpecialistImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManagerForSpecialist {
    Scanner scanner = new Scanner(System.in);
    ManageServiceForSpecialist forSpecialist = new ManagerServiceForSpecialistImpl();
    public void determineSingUp(){
        List<RequestForConfirmation> list = forSpecialist.RequestList();
        List<RequestForConfirmation> accept = new ArrayList<>();
        List<RequestForConfirmation> unAccept = new ArrayList<>();
        for(RequestForConfirmation request:list){
            System.out.print(request.getId()+"  ");
            System.out.print(request.getFirstName()+"  ");
            System.out.print(request.getLastName()+"  ");
            System.out.print(request.getAboutMe()+"  ");
            System.out.print(request.getTime()+"  ");
            System.out.println("if confirmation insert 'y' or insert 'n'");
            char check=scanner.next().charAt(0);
            switch (check){
                case 'y':
                    accept.add(request);
                case 'n':
                    unAccept.add(request);
                default:
            }
        }
        forSpecialist.acceptRequest(accept);
       // unAcceptList(unAccept);
    }
    private void acceptList(List<RequestForConfirmation> request){
        forSpecialist.acceptRequest(request);
    }
    private void unAcceptList(List<RequestForConfirmation> request){
            forSpecialist.changeStatusForRequest(request);
    }
    public void determineForRequestSpecialty() {
        List<RequestForNewSpecialization> list = forSpecialist.findNewRequest();
        List<RequestForNewSpecialization> accept = new ArrayList<>();
        List<RequestForNewSpecialization> unAccept = new ArrayList<>();
        for (RequestForNewSpecialization request : list) {
//            System.out.println(request.getSpecialist());
            System.out.println(request.getService().getName());
            System.out.println("if confirmation insert 'y' or insert 'n'");
            char check = scanner.next().charAt(0);
            switch (check) {
                case 'y':
                    request.setStatusUser(UserStatus.CONFIRMED);
                    accept.add(request);
                    System.out.println(request.getIdS());
                    break;
                case 'n':
                    unAccept.add(request);
                    continue;
                default:
            }
        }
        handleRequestNew(accept);
        removeFromRequestSpecialty(unAccept);
    }
    private void handleRequestNew(List<RequestForNewSpecialization> accept){
        forSpecialist.handleRequestForSpecialization(accept);
    }
    private void removeFromRequestSpecialty(List<RequestForNewSpecialization> list){
                forSpecialist.unAccept(list);

    }
    public void search(){
        Specialist specialist = optionForSearch();
        System.out.println(specialist);
            var list= forSpecialist.search(specialist);
            list.forEach(System.out::println);

    }
    private Specialist optionForSearch(){
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

        return new Specialist(null,null,fName,lName,email,null,status1);

    }
    private String checker(){
        String string = scanner.next();
        if (string.equals("no"))
            return null;
        else
            return string;

    }




}
