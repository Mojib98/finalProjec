package com.finalProject.Project.service.imp;

import com.finalProject.Project.entity.Expert;
import com.finalProject.Project.entity.SubService;
import com.finalProject.Project.repository.interfaces.ServiceRepository;
import com.finalProject.Project.repository.interfaces.SubServiceRepository;
import com.finalProject.Project.service.interfaces.ManageServiceForService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ServicesServiceImpl implements ManageServiceForService {
    private final ServiceRepository serviceRepository;
    private final SubServiceRepository subServiceRepository;

    public ServicesServiceImpl(ServiceRepository serviceRepository, SubServiceRepository subServiceRepository) {
        this.serviceRepository = serviceRepository;
        this.subServiceRepository = subServiceRepository;
    }


    @Override
    @Transactional
    public List<com.finalProject.Project.entity.Service> showAllService() {
        List<com.finalProject.Project.entity.Service> services;
        services = (List<com.finalProject.Project.entity.Service>) serviceRepository.findAll();
        return services;
    }

    @Override
    @Transactional
    public void insertService(com.finalProject.Project.entity.Service service) {
        checkUniqService(service);
        serviceRepository.save(service);
    }

    @Override
    @Transactional
    public void insertSubService(SubService service) {
        checkUniqSubService(service);
        subServiceRepository.save(service);
    }

    @Transactional
    public com.finalProject.Project.entity.Service findServiceByName(String name) {
        return serviceRepository.findByName(name);
    }

    @Transactional
    void checkUniqService(com.finalProject.Project.entity.Service service) {
        com.finalProject.Project.entity.Service service1 = serviceRepository.findByName(service.getName());
        if (service1 != null)
            throw new RuntimeException("this class exists");
    }

    @Transactional
    void checkUniqSubService(SubService service) {
        SubService service1 = subServiceRepository.findByName(service.getName());
        if (service1 != null) {
            throw new RuntimeException("this class exists");
        }
    }

    public List<SubService> showAllSubService() {
        return (List<SubService>) subServiceRepository.findAll();
    }
}
