package org.project.app;

import org.project.entity.RequestForConfirmation;
import org.project.service.ManageServiceForSpecialist;
import org.project.service.imp.ManagerServiceForSpecialistImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManagerForSpecialist {
    Scanner scanner = new Scanner(System.in);
    ManageServiceForSpecialist forSpecialist = new ManagerServiceForSpecialistImpl();
    public void determine(){
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
    private void acceptList(List<RequestForConfirmation>request){
        forSpecialist.acceptRequest(request);

    }
    private void unAcceptList(List<RequestForConfirmation> request){
            forSpecialist.unAcceptRequestConfirm(request);
    }

}
