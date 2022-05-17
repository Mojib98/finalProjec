package com.finalProject.Project.controller;

import com.finalProject.Project.entity.Service;
import com.finalProject.Project.entity.SubService;
import com.finalProject.Project.entity.dto.ServiceDto;
import com.finalProject.Project.service.imp.ServicesServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public void addSubService(){}
    public List<Service> showAllService(){
        return null;
    }
    public List<SubService> showAllSubService(){
        return null;
    }
    //search do here
}
