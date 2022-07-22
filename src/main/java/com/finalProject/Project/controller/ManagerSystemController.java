package com.finalProject.Project.controller;

import com.finalProject.Project.entity.Service;
import com.finalProject.Project.entity.SubService;
import com.finalProject.Project.entity.dto.ServiceDto;
import com.finalProject.Project.service.interfaces.ManageServiceForService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/service")
public class ManagerSystemController {
    private final ModelMapper modelMapper;
    private final ManageServiceForService servicesService;

    @PostMapping("/addservice")
    public void addService(ServiceDto service) {
        System.out.println(service);
        servicesService.insertService(service.getServiceName());
    }

    @PostMapping("/addsub")
    public void addSubService(@ModelAttribute ServiceDto serviceDto) {
        TypeMap<ServiceDto, SubService> typeMap = modelMapper.getTypeMap(ServiceDto.class, SubService.class);
        if (typeMap == null) {
            modelMapper.addMappings(new PropertyMap<ServiceDto, SubService>() {
                @Override
                protected void configure() {
                    map().setName(source.getSubServiceName());
                }
                // if not  already added
            });
        }
        SubService subService = modelMapper.map(serviceDto, SubService.class);
        System.out.println(serviceDto);
        System.out.println(subService);
        System.out.println(serviceDto.getServiceName());
        Service service = findServiceByName(serviceDto.getServiceName());
        System.out.println(service + "check111");
        subService.setService(service);
        servicesService.insertSubService(subService);
    }

    @GetMapping("/allService")
    public List<ServiceDto> showAllService() {

        var listService = servicesService.showAllService();
        System.out.println(listService);
        return Arrays.asList(modelMapper.map(listService, ServiceDto[].class));
    }

    @PostMapping("/sub")
    public List<ServiceDto> showAllSubService(ServiceDto serviceDto) {
        TypeMap<SubService, ServiceDto> typeMap = modelMapper.getTypeMap(SubService.class, ServiceDto.class);
        if (typeMap == null) {
            modelMapper.addMappings(new PropertyMap<SubService, ServiceDto>() {
                @Override
                protected void configure() {
                    map().setSubServiceName(source.getName());
                    skip(destination.getServiceName());
                }
            });
        }
        var listService = servicesService.showAllSubServiceById(serviceDto.getId());
        return Arrays.asList(modelMapper.map(listService, ServiceDto[].class));
    }

    @GetMapping("/sub")
    public List<ServiceDto> showAllSubService() {
        TypeMap<SubService, ServiceDto> typeMap = modelMapper.getTypeMap(SubService.class, ServiceDto.class);
        if (typeMap == null) {
            modelMapper.addMappings(new PropertyMap<SubService, ServiceDto>() {
                @Override
                protected void configure() {
                    map().setSubServiceName(source.getName());
                }
            });
        }
        var listService = servicesService.showAllSubService();
        return Arrays.asList(modelMapper.map(listService, ServiceDto[].class));
    }

    //search do here
    public Service findServiceByName(String name) {
        Service service = servicesService.findServiceByName(name);
        if (service == null)
            throw new RuntimeException("class not find");
        return service;
    }


}
