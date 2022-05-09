package org.project.service.imp;

import org.hibernate.SessionFactory;
import org.project.entity.Customer;
import org.project.entity.Service;
import org.project.entity.SubService;
import org.project.repository.imp.RepositoryService;
import org.project.repository.imp.SessionFactorySingleton;
import org.project.service.interfaces.ServiceForService;

import java.util.List;

public class ServiceForServiceImpl extends GenericServiceImpl<Service>{
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    RepositoryService serviceRegistry = new RepositoryService();
    public void addService(Service service){
            try {
                checkUniqueService(service);
                insert(service);
            } catch (Exception e) {
                System.out.println(e.getMessage());

            }
        }


    public void addSubService(SubService subService) {
        Service service = findServiceByName(subService.getService().getName());
        if (service == null) {
            throw new RuntimeException("not find");

        }
        try (var session = sessionFactory.getCurrentSession()){
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                serviceRegistry.insertSubService(subService);
            transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());

            }
        }
    }

    //    }
    private void checkUniqueService(Service service){
                Service service1=null;
                service1 = findServiceByName(service.getName());
                if (service1 !=null)
                    throw new RuntimeException("this class exist");

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
    public List<SubService> showAllSubService(){
        List<SubService> subServices = null;
        try (var session = sessionFactory.getCurrentSession()){
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                subServices=serviceRegistry.findAll();
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());

            }
        }
        return subServices;

    }
    public List<Service> findAllService(){
        List<Service> services = null;
        try (var session = sessionFactory.getCurrentSession()){
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                services=serviceRegistry.findAllService();
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());

            }
        }
        return services;
    }
    public Service findServiceByName(String name){
        Service service = null;
        try (var session = sessionFactory.getCurrentSession()){
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                service = serviceRegistry.findServiceByName(name);

                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
                throw e;

            }
        }
        return service;
    }


}
