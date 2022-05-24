package com.finalProject.Project.controller;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.finalProject.Project.app.Utility;
import com.finalProject.Project.entity.*;
import com.finalProject.Project.entity.dto.OfferDto;
import com.finalProject.Project.entity.dto.OrderDto;
import com.finalProject.Project.service.imp.CustomerServiceImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerServiceImpl service;
    private Customer customer = new Customer();

    {
        customer.setId(113);
    }

    private Scanner scanner = new Scanner(System.in);
    private Utility utility = new Utility();
    private final ModelMapper modelMapper = new ModelMapper();


    @PostMapping("/createorder")
    public void createOrder(@ModelAttribute OrderDto orderDto) {


        service.insertOrder(orderDto, customer);


    }
    @GetMapping("/myOffer{id}")
    public List<OfferDto> findOfferForOrder(@RequestParam String id) {
        modelMapper.addMappings(new PropertyMap<Offer, OfferDto>() {
            @Override
            protected void configure() {
                skip(destination.getSubServiceId());
                skip(destination.getExpertName());
            }
        });
        Integer ids=Integer.parseInt(id);
        var list = service.findOfferByOrderId(ids);
        return Arrays.asList(modelMapper.map(list, OfferDto[].class));
    }
    @GetMapping("/myOrder")
    public List<OrderDto> seeMyOrder() {
        modelMapper.addMappings(new PropertyMap<Order, OrderDto>() {
            @Override
            protected void configure() {
                map().setCustomersName(source.getCustomers().getFirstName());
                map().setSubServiceName(source.getSubService().getName());
            }
        });

        List<Order> order = service.findMyOrder(customer.getId());
        return Arrays.asList(modelMapper.map(order, OrderDto[].class));


    }

    private void checkPrice(SubService service, Integer price) {
        if (service.getBasePrice() > price)
            throw new RuntimeException("bad price");
    }

    public void changePassword() {
        String newPassword = utility.setPassword();
        service.changePassword(this.customer, newPassword);

    }
    @PostMapping("/choiceOffer")
    public void choiceOffer(@ModelAttribute OfferDto offerDto) {
        System.out.println(offerDto);

            service.choiceOffer(offerDto.getId());
    }

    public void payForOrder() {
        try {
            List<Order> acceptOffer = service.myDownOrder(customer.getId());
            acceptOffer.stream().forEach(System.out::println);
            Integer orderId = scanner.nextInt();
            Order order = acceptOffer.stream()
                    .filter(p -> p.getId().equals(orderId))
                    .findFirst().get();
            System.out.println("insert rate");
            Integer rate = scanner.nextInt();
            service.paying(order, rate);
            addComment(order);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void addComment(Order order) {
        try {
            System.out.println("insert comment");
            String comment = scanner.nextLine();
            Comment comment1 = new Comment(null, null, comment, this.customer);
            Offer offer = order.getOffer();
            service.addComment(offer, comment1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sortOfferByPrice(List<Offer> offers) {
        var offerss = offers.stream().sorted(new Comparator<Offer>() {
            @Override
            public int compare(Offer offer, Offer t1) {
                return offer.getOfferPrice().compareTo(t1.getOfferPrice());
            }
        }).collect(Collectors.toList());
        for (Offer offer : offerss) {
            System.out.println(offer.getId());
            System.out.println(offer.getOfferPrice());

        }
    }

}
