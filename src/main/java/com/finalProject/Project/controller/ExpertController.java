package com.finalProject.Project.controller;

import com.finalProject.Project.app.Utility;
import com.finalProject.Project.entity.*;
import com.finalProject.Project.entity.dto.OfferDto;
import com.finalProject.Project.entity.dto.OrderDto;
import com.finalProject.Project.entity.dto.ServiceDto;
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
    public void requestForSpecialty(@RequestBody ServiceDto service){
        expertService.requestForSpecialty(expert,service.getServiceName());
    }
    @GetMapping
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
    @PostMapping("/offer")
    public void writeOffer(@RequestBody OfferDto offerDto) {
        modelMapper.addMappings(new PropertyMap<OfferDto, Offer>() {
            @Override
            protected void configure() {
//                skip(destination.getCommentId());
//                skip(destination.getOrderId());
//                skip(destination.getExpert());
                skip(destination.getId());
            }
        });
        Order order = expertService.findOrderById(offerDto.getOrderId());
        checkingOffer(offerDto,order,order.getSubService());
        Offer offer = modelMapper.map(offerDto,Offer.class);
        expertService.insertOffer(offer, order,expert);
        System.out.println(offerDto);
    }
    public void seeOrderForStart(){
        Order order = null;
        List<Order> list = expertService.findOrders(expert);
        for (Order orders:list){
            System.out.print(orders.getDescribe()+" ");
            System.out.print(orders.getOfferPrice()+" ");
            System.out.print(orders.getTimeForWork()+" ");
            System.out.print(orders.getId()+" ");
        };
        System.out.println("insert id");
        Integer id=utility.giveIntegerInput();
        order= list.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst().get();
    }
    public void changePassword(){}
    private boolean isCurrentTime(LocalDateTime orderTime,LocalDateTime offerTime){
        if (offerTime.equals(orderTime)){
            return true;
        }
        return false;
    }
    public void startWork() {
        try {
            List<Offer> acceptOffers = expertService.findOfferForAction(expert.getId(), WorkStatus.WAIT_FOR_ARRIVE);
            for (Offer a : acceptOffers) {
                System.out.println(a.getId());
                System.out.println(a.getWorkTime());
            }
            System.out.println("please select one of for start ");
            Integer id = scanner.nextInt();
            expertService.changeWorkByExpert(id, WorkStatus.START);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    private void checkingOffer(OfferDto offerDto,Order order,SubService service){
        if (offerDto.getWorkTime().isBefore(order.getTimeForWork()) )
            throw new RuntimeException("time not current try");
        if (offerDto.getOfferPrice()<service.getBasePrice())
            throw new RuntimeException("your price lower range");

    }
}
