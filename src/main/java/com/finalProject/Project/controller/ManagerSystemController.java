package com.finalProject.Project.controller;

import com.finalProject.Project.entity.Service;
import com.finalProject.Project.entity.SubService;
import com.finalProject.Project.entity.dto.ServiceDto;
import com.finalProject.Project.service.imp.ServicesServiceImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ser")
public class ManagerSystemController {
    private final ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private ServicesServiceImpl servicesService;

    @Autowired
    ServicesServiceImpl service;
    @PostMapping("/service")
    public void addService(@RequestBody ServiceDto serviceDto){
        System.out.println(serviceDto.getServiceName());
        Service service = modelMapper.map(serviceDto,Service.class);
        System.out.println(service);
        servicesService.insertService(service);


    }
    @PostMapping
    public void addSubService(@RequestBody ServiceDto serviceDto){
        SubService subService = modelMapper.map(serviceDto,SubService.class);
        Service service = findServiceByName(serviceDto.getServiceName());
        System.out.println(service);
        subService.setCategory(service);
        servicesService.insertSubService(subService);


    }
    @GetMapping
    public List<ServiceDto> showAllService(){
        var listService= servicesService.showAllService();
        return Arrays.asList(modelMapper.map(listService, ServiceDto[].class));
    }
    @GetMapping("/sub")
    public List<ServiceDto> showAllSubService(){
        modelMapper.addMappings(new PropertyMap<SubService, ServiceDto>() {
            @Override
            protected void configure() {
                skip(destination.getServiceName());
            }
        });
        var listService= servicesService.showAllSubService();
        return Arrays.asList(modelMapper.map(listService, ServiceDto[].class));
    }
    //search do here
    public Service findServiceByName(String name){
        Service service = servicesService.findServiceByName(name);
        if (service == null)
            throw new RuntimeException("class not find");
        return service;
    }
}
