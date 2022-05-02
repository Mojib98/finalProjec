package org.project.service.imp;

import net.bytebuddy.implementation.bytecode.Throw;
import org.hibernate.SessionFactory;
import org.project.entity.Service;
import org.project.repository.imp.RepositoryService;
import org.project.repository.imp.SessionFactorySingleton;

import javax.imageio.spi.ServiceRegistry;
import java.util.List;

public class ServiceForServiceImpl extends GenericServiceImpl<Service> {
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

//    }
    private void checkUniqueService(Service service){
        Service service1 = null;
        try (var session = sessionFactory.getCurrentSession()){
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                 service1 = serviceRegistry.findByName(service.getName());
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());

            }
        }
        if (service1 !=null)
        throw new RuntimeException("this service is have");
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
    public Service findById(Integer id) {
        return super.findById(id);
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
                return serviceRegistry.findByName(name);
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
                return null;
            }
        }
    }
    public List<Service> findByCategory(String categoryName){
        List<Service> serviceList = null;
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
    public List<Service> showAllSpecialty(){
        List<Service> specialty = null;
        try (var session = sessionFactory.getCurrentSession()){
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                specialty=serviceRegistry.findJustSpecialty();
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());

            }
        }
        return specialty;

    }
    public void insertSpecialty(Service service){
        try {
            if (service.getCategory() == null)
                throw new RuntimeException("dont have cateGury");
            checkUniqueService(service);
            insert(service);
        }catch (Exception e){
            e.getMessage();;
        }
    }
}
