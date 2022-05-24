package com.finalProject.Project.controller;

import com.finalProject.Project.app.Utility;
import com.finalProject.Project.entity.*;
import com.finalProject.Project.entity.dto.OfferDto;
import com.finalProject.Project.entity.dto.OrderDto;
import com.finalProject.Project.entity.dto.ServiceDto;
import com.finalProject.Project.entity.dto.SpecialistDto;
import com.finalProject.Project.entity.enumeration.WorkStatus;
import com.finalProject.Project.service.imp.ExpertService;
import com.finalProject.Project.service.imp.ServicesServiceImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@RestController
@CrossOrigin
@RequestMapping("/exp")
public class ExpertController {
    private final ModelMapper modelMapper = new ModelMapper();

    Integer id;
    Expert expert = new Expert();
    {
        expert.setId(82);
    }
    List<Service> serviceList;
    Scanner scanner = new Scanner(System.in);

    @Autowired
    ExpertService expertService;
    @Autowired
    ServicesServiceImpl servicesService;
    Utility utility = new Utility();
    @GetMapping("/showspec")
    public void showListOfSpecialty(){
        this.serviceList = servicesService.showAllService();
        for (Service service:serviceList){
            System.out.println(service.getName());
        }
    }
    @PostMapping("/request")
    public void requestForSpecialty(@ModelAttribute SpecialistDto specialistDto){
        System.out.println(specialistDto);
        expertService.requestForSpecialty(expert,specialistDto.getServiceName());
    }
    @GetMapping("/seeorder")
    public List<OrderDto> seeOrders(){
        modelMapper.addMappings(new PropertyMap<Order, OrderDto>() {
            @Override
            protected void configure() {
                map().setCustomersName(source.getCustomers().getFirstName());
                map().setSubServiceName(source.getSubService().getName());
            }
        });
        List<com.finalProject.Project.entity.Order> list = expertService.findOrders(expert);
        return Arrays.asList(modelMapper.map(list, OrderDto[].class));
    }
    @PostMapping("/writeOffer")
    public void writeOffer(@ModelAttribute OfferDto offerDto) {
        modelMapper.addMappings(new PropertyMap<OfferDto, Offer>() {
            @Override
            protected void configure() {
//                skip(destination.getCommentId());
//                skip(destination.getOrderId());
//                skip(destination.getExpert());
                skip(destination.getId());
            }
        });
        LocalDateTime localDateTime = LocalDateTime.parse(offerDto.getLocalDateTime());
        Order order = expertService.findOrderById(offerDto.getOrderId());
        Offer offer = modelMapper.map(offerDto,Offer.class);
        offer.setWorkTime(localDateTime);
//        checkingOffer(offer,order,order.getSubService());
        expertService.insertOffer(offer, order,expert);
        System.out.println(offerDto);
       expertService.insertOffer(offer,order,expert);
        System.out.println(offerDto);
    }
    @GetMapping("/startOrder")
    public List<OrderDto> seeOrderForStart(){
        List<Offer> acceptOffers = expertService.findOfferForAction(expert.getId(), WorkStatus.START);
//        modelMapper.addMappings(new PropertyMap<Order, OrderDto>() {
//            @Override
//            protected void configure() {
//                map().setCustomersName(source.getCustomers().getFirstName());
//                map().setSubServiceName(source.getSubService().getName());
//            }
//        });
        List<Order> list = expertService.findOrdersForStart(expert);
        System.out.println(list);
        return Arrays.asList(modelMapper.map(list, OrderDto[].class));
    }
    public void changePassword(){}
    private boolean isCurrentTime(LocalDateTime orderTime,LocalDateTime offerTime){
        if (offerTime.equals(orderTime)){
            return true;
        }
        return false;
    }
    @PostMapping("/startwork")
    public void startWork(@ModelAttribute OrderDto orderDto) {

        System.out.println(id);
        expertService.startWork(orderDto.getId());
    }
    public void downWork() {
        try {
            List<Offer> acceptOffers = expertService.findOfferForAction(expert.getId(), WorkStatus.START);
            for (Offer a : acceptOffers) {
                System.out.println(a.getId());
                System.out.println(a.getWorkTime());
            }
            System.out.println("please select one of for start ");
            Integer id = scanner.nextInt();
            expertService.changeWorkByExpert(id, WorkStatus.DONE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void checkingOffer(Offer offer,Order order,SubService service){
        if (offer.getWorkTime().isBefore(order.getTimeForWork()) )
            throw new RuntimeException("time not current try");
        if (offer.getOfferPrice()<service.getBasePrice())
            throw new RuntimeException("your price lower range");

    }
    @GetMapping("/serviceList")
    public List<ServiceDto> showAllService(){
        var listService= servicesService.showAllService();
        return Arrays.asList(modelMapper.map(listService, ServiceDto[].class));
    }
}
