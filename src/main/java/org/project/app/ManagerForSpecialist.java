package org.project.app;

import org.project.entity.RequestForConfirmation;
import org.project.entity.RequestForNewSpecialization;
import org.project.entity.enumeration.Statuses;
import org.project.service.ManageServiceForSpecialist;
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
            System.out.println(request);
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
        acceptList(accept);
        unAcceptList(unAccept);
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
            System.out.println(request.getSpecialist());
            System.out.println(request.getIdS());
            System.out.println("if confirmation insert 'y' or insert 'n'");
            char check = scanner.next().charAt(0);
            switch (check) {
                case 'y':
                    request.setStatuses(Statuses.CONFIRMED);
                    accept.add(request);
                    System.out.println(request.getIdS());
                case 'n':
                    continue;
                default:
            }
        }
        acceptLists(accept);
    }

    private void acceptLists(List<RequestForNewSpecialization> request){
        forSpecialist.handleRequestForSpecialization(request);
    }



}
