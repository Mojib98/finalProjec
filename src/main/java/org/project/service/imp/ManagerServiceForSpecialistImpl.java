package org.project.service.imp;

import org.hibernate.SessionFactory;
import org.project.entity.RequestForConfirmation;
import org.project.entity.RequestForNewSpecialization;
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
                for (RequestForConfirmation request1:request){
                    Specialist specialist = new Specialist();
                    request1.setStatus(Statuses.CONFIRMED);
                    specialist.setFirstName(request1.getFirstName());
                    specialist.setLastName(request1.getLastName());
                    specialist.setPassword(request1.getPassword());
                    specialist.setEmail(request1.getEmail());
                    specialist.setStatus(Statuses.CONFIRMED);
                    insert(specialist);
                    request1.setStatus(Statuses.ACTIVE);
                    repositorySpecialist.changeStatus(request1);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());

            }


    }
    public void unAcceptRequestConfirm(List<RequestForConfirmation> request){
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                for (RequestForConfirmation request1:request){
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
    public void handleRequestForSpecialization(List<RequestForNewSpecialization> request) {
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
                request=repositorySpecialist.RequestList();
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
}
