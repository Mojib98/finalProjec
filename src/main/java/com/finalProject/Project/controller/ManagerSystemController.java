package com.finalProject.Project.controller;
import com.finalProject.Project.entity.Offer;
import com.finalProject.Project.entity.Order;
import com.finalProject.Project.entity.Service;
import com.finalProject.Project.entity.SubService;
import com.finalProject.Project.entity.dto.OfferDto;
import com.finalProject.Project.entity.dto.OrderDto;
import com.finalProject.Project.entity.dto.ServiceDto;
import com.finalProject.Project.service.imp.ManagerProfileServiceImpl;
import com.finalProject.Project.service.imp.ServicesServiceImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/service")
public class ManagerSystemController {
    private final ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private ServicesServiceImpl servicesService;
    @Autowired
    private ManagerProfileServiceImpl managerProfileService;

    @Autowired
    ServicesServiceImpl service;

    @PostMapping("/addservice")
    public void addService( ServiceDto service) {
//        Service service = modelMapper.map(serviceDto,Service.class);
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
//                skip(destination.getService());
                    map().setName(source.getSubServiceName());
//                skip(source.getServiceName());
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
//                map().setServiceName(source..getName());
                    map().setSubServiceName(source.getName());
                    skip(destination.getServiceName());
                }
            });
        }
        var listService = servicesService.showAllSubServiceById(serviceDto.getId());
        return Arrays.asList(modelMapper.map(listService, ServiceDto[].class));
    }

        @GetMapping("/sub")
        public List<ServiceDto> showAllSubService(){
            modelMapper.addMappings(new PropertyMap<SubService, ServiceDto>() {
                @Override
                protected void configure() {
    //                map().setServiceName(source.getService().getName());
                    map().setSubServiceName(source.getName());
                }
            });
            var listService= servicesService.showAllSubService();
    //        System.out.println(listService.get(0).getService().getName());
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
