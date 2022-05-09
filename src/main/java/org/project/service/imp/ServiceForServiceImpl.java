package org.project.service.imp;

import org.hibernate.SessionFactory;
import org.project.entity.Customer;
import org.project.entity.Service;
import org.project.entity.SubService;
import org.project.repository.imp.RepositoryService;
import org.project.repository.imp.SessionFactorySingleton;
import org.project.service.interfaces.ServiceForService;

import java.util.List;

public class ServiceForServiceImpl extends GenericServiceImpl<Service> implements ServiceForService {
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    RepositoryService serviceRegistry = new RepositoryService();
    public void addService(Service service){
//        try (var session = sessionFactory.getCurrentSession()){
//            var transaction = session.getTransaction();
            try {
//                transaction.begin();

                checkUniqueService(service);
                insert(service);
//                transaction.commit();
            } catch (Exception e) {
//                transaction.rollback();
                System.out.println(e.getMessage());

            }
        }

    @Override
    public void addSubService(SubService service) {

    }

    //    }
    private void checkUniqueService(Service service){
        Service service1 = null;
        try (var session = sessionFactory.getCurrentSession()){
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());

            }
        }
  /*      if (service1 !=null)
        throw new RuntimeException("this service is have");*/
    }

    @Override
    public Service insert(Service service) {
        return super.insert(service);
    }

    @Override
    public Service update(Service service) {
        return super.update(service);
    }

    @Override
    public void remove(Service service) {
        super.remove(service);
    }



    @Override
    public List<Service> findAll() {
        return super.findAll();
    }
    public Service findByName(String name){
        try (var session = sessionFactory.getCurrentSession()){
            var transaction = session.getTransaction();
            try {
                transaction.begin();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
                return null;
            }
        }
        return null;
    }
    public List<SubService> findByCategory(String categoryName){
        List<SubService> serviceList = null;
        try (var session = sessionFactory.getCurrentSession()){
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                serviceList = serviceRegistry.findByCategory(categoryName);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());

            }
        }
        return serviceList;
    }
    public List<Service> findJustCategory(){
        List<Service> category = null;
        try (var session = sessionFactory.getCurrentSession()){
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                category=serviceRegistry.findJustCategory();
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());

            }
        }
        return category;
    }
    public List<SubService> showAllSubService(){
        List<SubService> subServices = null;
        try (var session = sessionFactory.getCurrentSession()){
            var transaction = session.getTransaction();
            try {
                transaction.begin();
//                subServices=serviceRegistry.findJustSpecialty();
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());

            }
        }
        return subServices;

    }
    public void insertSpecialty(Service service){
        try {
            if (service == null)
                throw new RuntimeException("dont have cateGury");
            checkUniqueService(service);
            insert(service);
        }catch (Exception e){
            e.getMessage();;
        }
    }
    public List<Customer> search(Customer customer) {
        List<Customer> list = null;
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
                System.out.println(e.getMessage());

            }

        }
        return list;

    }

}
