package org.project.service.imp;

import org.hibernate.SessionFactory;
import org.project.entity.*;
import org.project.entity.enumeration.UserStatus;
import org.project.repository.imp.ManageRepositoryExpert;
import org.project.repository.imp.SessionFactorySingleton;
import org.project.service.interfaces.ManageServiceForExpert;

import java.util.List;

public class ManagerServiceForExpertImpl extends GenericServiceImpl<BaseClass> implements ManageServiceForExpert {
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    private final ManageRepositoryExpert repositorySpecialist = new ManageRepositoryExpert();
    ServiceForServiceImpl service = new ServiceForServiceImpl();

    @Override
    public void changeStatusExpert(Expert expert) {
            update(expert);
    }

    @Override
    public void handleRequestForExpert(List<Expert> accepted, List<Expert> unAccepted) {
        for (Expert accept:accepted){
            accept.setStatus(UserStatus.CONFIRMED);
            changeStatusExpert(accept);
        }
        for (Expert unAccept:unAccepted){
            unAccept.setStatus(UserStatus.UNCONFIRMED);
            changeStatusExpert(unAccept);

        }
           }

    @Override
    public List<Expert> search(Expert expert) {
        List<Expert> list = null;
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                list=repositorySpecialist.search(expert);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
                System.out.println(e.getMessage());

            }

        }
        return list;
    }

    @Override
    public List<Expert> requestListSingUp() {
        List<Expert> list = null;
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                list=repositorySpecialist.requestList();
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
                System.out.println(e.getMessage());

            }

        }
        return list;

    }
    public List<Specialty> requestListSpecialty(){
        List<Specialty> list = null;
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                list=repositorySpecialist.requestForSpecialty();
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
                System.out.println(e.getMessage());

            }

        }
        return list;
    }
    public void handelRequestForSpecialty(List<Specialty> accepted, List<Specialty> unAccepted) {
        for (Specialty accept:accepted){
            accept.setStatus(UserStatus.CONFIRMED);
            update(accept);
        }
        for (Specialty unAccept:unAccepted){
            unAccept.setStatus(UserStatus.UNCONFIRMED);
            update(unAccept);
//            removeSpecialty(unAccept);


        }
    }
    public void removeSpecialty(Specialty specialty){
        remove(specialty);
    }

   /* @Override
    public void changeStatus(Expert specialist) {
        if (specialist.getStatus().equals(UserStatus.ACTIVE))
            specialist.setStatus(UserStatus.INACTIVE);
        else
            specialist.setStatus(UserStatus.ACTIVE);
        update(specialist);

    }

    @Override
    public void acceptRequest(List<RequestForConfirmation> request) {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();


//                    changeStatusForRequest(request);
                    transaction.commit();

                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                transaction.rollback();
            }

        }
    }

    public void changeStatusForRequest(List<RequestForConfirmation> request) {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                for (RequestForConfirmation request1 : request) {
                    if (request1.getStatus().equals(UserStatus.AWAITING_CONFIRMATION))
                    request1.setStatus(UserStatus.UNCONFIRMED);
                    repositorySpecialist.changeStatus(request1);
                }
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());

            }

        }
    }


    @Override
    public void unAccept(List<Expert> experts) {
        if (experts == null){
            throw new RuntimeException("list is empty");
        }
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                for (Expert expert:experts){
                    transaction.begin();
                    expert.setStatus(UserStatus.UNCONFIRMED);
                    repositorySpecialist.unAccept(expert);
                    transaction.commit();
                }
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());

            }

        }

    }



    @Override
    public void handleRequestForSpecialization(List<RequestForNewSpecialization> request) {
        if (request == null){
            throw new RuntimeException("list is empty");
        }
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                for (RequestForNewSpecialization request1 : request) {
                    transaction.begin();
                    System.out.println(request1.getService());
                    Service service = new Service(request1.getService().getId(), null, request1.getService().getName());
                    Expert specialist = request1.getSpecialist();
                    repositorySpecialist.handleRequestForSpecialization(service,specialist);
                    transaction.commit();
                 }
                } catch(Exception e){
                    transaction.rollback();
                    System.out.println(e.getMessage());

                }


        }
        removeRequest(request);


    }


    @Override
    public List<Expert> search(Expert specialist) {
        List<Expert> list = null;
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                list=repositorySpecialist.search(specialist);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
                System.out.println(e.getMessage());

            }

        }
        return list;

    }




    @Override
    public Expert findById(java.lang.Integer id) {
        return super.findById(id);
    }*/
}
