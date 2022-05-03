package org.project.service.imp;

import org.hibernate.SessionFactory;
import org.project.entity.RequestForConfirmation;
import org.project.entity.RequestForNewSpecialization;
import org.project.entity.Service;
import org.project.entity.Specialist;
import org.project.entity.enumeration.Statuses;
import org.project.repository.imp.ManageRepositorySpecialist;
import org.project.repository.imp.SessionFactorySingleton;
import org.project.service.ManageServiceForSpecialist;

import java.util.List;
import java.util.Properties;

public class ManagerServiceForSpecialistImpl extends GenericServiceImpl<Specialist> implements ManageServiceForSpecialist {
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    private final ManageRepositorySpecialist repositorySpecialist = new ManageRepositorySpecialist();
    ServiceForServiceImpl service = new ServiceForServiceImpl();


    @Override
    public void changeStatus(Specialist specialist) {
        if (specialist.getStatus().equals(Statuses.ACTIVE))
            specialist.setStatus(Statuses.INACTIVE);
        else
            specialist.setStatus(Statuses.ACTIVE);
        update(specialist);

    }

    @Override
    public void acceptRequest(List<RequestForConfirmation> request) {
        try {
            for (RequestForConfirmation request1 : request) {
                Specialist specialist = new Specialist();
                request1.setStatus(Statuses.CONFIRMED);
                specialist.setFirstName(request1.getFirstName());
                specialist.setLastName(request1.getLastName());
                specialist.setPassword(request1.getPassword());
                specialist.setEmail(request1.getEmail());
                specialist.setStatus(Statuses.CONFIRMED);
                insert(specialist);

            }
            changeStatusForRequest(request);
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }


    }

    public void changeStatusForRequest(List<RequestForConfirmation> request) {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                for (RequestForConfirmation request1 : request) {
                    if (request1.getStatus().equals(Statuses.AWAITING_CONFIRMATION))
                    request1.setStatus(Statuses.UNCONFIRMED);
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
    public void acceptSpecial(List<RequestForNewSpecialization> request) {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());

            }

        }

    }

    @Override
    public List<RequestForNewSpecialization> findNewRequest() {
        List<RequestForNewSpecialization> list = null;
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                list = repositorySpecialist.findNewRequest();
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());

            }

        }
        return list;

    }

    @Override
    public void handleRequestForSpecialization(List<RequestForNewSpecialization> request) {
   /*     try {
            for (RequestForNewSpecialization request1 : request) {
                System.out.println(request1.getService());
                System.out.println(request1.getSpecialist());
                Service service = new Service(request1.getService().getId(),null,request1.getService().getName());
                Specialist specialist = request1.getSpecialist();
                Service service1 = request1.getService();
                specialist.addService(service);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }*/
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                for (RequestForNewSpecialization request1 : request) {
                    System.out.println(request1.getService());
//                    System.out.println(request1.getSpecialist());
                    Service service = new Service(request1.getService().getId(), null, request1.getService().getName());
//                    System.out.println(service);
                    Specialist specialist = request1.getSpecialist();
//                    Service service1 = request1.getService();
//                    session.update(specialist);
//                    specialist.getServices().add(service);
                    repositorySpecialist.handleRequestForSpecialization(service,specialist);
                    transaction.commit();
                }
                } catch(Exception e){
                    transaction.rollback();
                    System.out.println(e.getMessage());

                }


        }


    }


    @Override
    public List<Properties> search(Properties properties) {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());

            }

        }
        return null;
    }

    @Override
    public List<RequestForConfirmation> RequestList() {
        List<RequestForConfirmation> request = null;
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                request = repositorySpecialist.requestList();
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());

            }

        }
        return request;
    }

    @Override
    public Specialist insert(Specialist specialist) {
        return super.insert(specialist);
    }

    @Override
    public Specialist update(Specialist specialist) {
        return super.update(specialist);
    }

    private void removeRequest(List<RequestForNewSpecialization> request) {
        for (RequestForNewSpecialization request1 : request) {
            repositorySpecialist.removeRequestForNewSpec(request1);
        }
    }

    @Override
    public Specialist findById(Integer id) {
        return super.findById(id);
    }
}
