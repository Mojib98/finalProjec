package com.finalProject.Project.controller;
import com.finalProject.Project.entity.*;
import com.finalProject.Project.entity.dto.OfferDto;
import com.finalProject.Project.entity.dto.OrderDto;
import com.finalProject.Project.entity.dto.UserDto;
import com.finalProject.Project.service.imp.CustomerServiceImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerServiceImpl service;
    private Customer customer = new Customer();

    {
        customer.setId(189);
    }

    private final ModelMapper modelMapper = new ModelMapper();


    @PostMapping("/createorder")
    public void createOrder(@ModelAttribute OrderDto orderDto) {

//        System.out.println(orderDto.getSubServiceId());
        System.out.println(orderDto);
        service.insertOrder(orderDto, customer);


    }
    @GetMapping("/myOffer")
    public List<OfferDto> findOfferForOrder(@RequestParam String id) {
        System.out.println("id s:"+id);
        modelMapper.addMappings(new PropertyMap<Offer, OfferDto>() {
            @Override
            protected void configure() {
//                skip(destination.getSubServiceId());
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

        List<Order> order = service.findMyOrderstatus(customer.getId());
        return Arrays.asList(modelMapper.map(order, OrderDto[].class));
    }

    private void checkPrice(SubService service, Integer price) {
        if (service.getBasePrice() > price)
            throw new RuntimeException("bad price");
    }

    @PostMapping("/choiceOffer")
    public void choiceOffer(@ModelAttribute OfferDto offerDto) {
        System.out.println(offerDto);

            service.choiceOffer(offerDto.getId());
    }
    @GetMapping("/downOrder")
    public List<OrderDto> ListDownOffer() {
        TypeMap<Order,OrderDto> typeMap = modelMapper.getTypeMap(Order.class, OrderDto.class);
        List<Order> downOrder = service.myDownOrder(customer.getId());
        if (typeMap == null) {
            System.out.println(downOrder);
            modelMapper.addMappings(new PropertyMap<Order, OrderDto>() {
                @Override
                protected void configure() {
//                map().setExpertName(source.getExpert());
//                map().setOfferPrice(source.getOfferPrice());
                    skip(destination.getCustomersName());
//                skip(destination.getLocalDateTime());
                }
            });
        }
        return Arrays.asList(modelMapper.map(downOrder, OrderDto[].class));

    }
    @PostMapping("/paying")
    public void paying(@ModelAttribute OrderDto orderDto) {
            service.paying(orderDto);
    }
    @PostMapping("/addcomment")
    public void commenting(OrderDto orderDto){
        service.addComment(orderDto.getCommentText(),customer,orderDto.getId());

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
    @GetMapping("/allmyorder")
    public List<OrderDto> AllMyOffer(){
        modelMapper.addMappings(new PropertyMap<Order, OrderDto>() {
            @Override
            protected void configure() {
                map().setCustomersName(source.getCustomers().getFirstName());
                map().setSubServiceName(source.getSubService().getName());
            }
        });

        List<Order> order = service.findMyOrder(customer.getId());
        return Arrays.asList(modelMapper.map(order, OrderDto[].class));    }
    @GetMapping("myInfo")
    public UserDto myInfo(){
        Customer customer = service.findMe(this.customer.getId());
        UserDto userDto = modelMapper.map(customer,UserDto.class);
        System.out.println(userDto);
        return userDto;
    }
    @PostMapping("/despositOnline")
    public void depositWallet(@ModelAttribute OrderDto orderDto){
        Integer customerId=customer.getId();
        Integer amount = orderDto.getAmount();
        //checking
        service.depositWallet(customerId,amount);
    }
    @PostMapping("/onlinePaying")
    public ResponseEntity<Object> OnlinePaying(@ModelAttribute OrderDto orderDto){
        System.out.println(orderDto);
        depositWallet(orderDto);
        paying(orderDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
    @GetMapping(value = "/redirect")
    public ResponseEntity<Void> redirect(@RequestParam String input){

        System.out.println(input);
        Map<String,String>  map = new HashMap<>();
        map.put("id","2");
        return ResponseEntity.

                status(HttpStatus.FOUND).

                location(URI.create("https://fullstackdeveloper.guru"))
        .build();
    }
    @GetMapping(value = "/redirect1")
    public RedirectView redirect2(){

//        System.out.println(input);


        RedirectView redirectView = new RedirectView();
//        redirectView.setContextRelative();
        redirectView.setUrl("/home/arkad/IdeaProjects/Project/src/main/resources/public/payment.html");

        return redirectView;

    }
    @GetMapping("/howMuch")
    public OfferDto findPrice(@RequestParam String id){
        Integer ids=Integer.parseInt(id);
        Integer price = service.OfferPrice(ids);
        OfferDto offerDto = new OfferDto();
        offerDto.setOfferPrice(price);
        return offerDto;


    }
    @PostMapping("/addcomment")
    public void addComment(@ModelAttribute OrderDto orderDto){
        service.addComment(orderDto.getCommentText(),customer,orderDto.getId());
    }
    public List<OrderDto> OrderForComment(){
        modelMapper.addMappings(new PropertyMap<Order, OrderDto>() {
            @Override
            protected void configure() {
                map().setCustomersName(source.getCustomers().getFirstName());
                map().setSubServiceName(source.getSubService().getName());
            }
        });

        List<Order> order = service.findPaidOrder(customer.getId());
        return Arrays.asList(modelMapper.map(order, OrderDto[].class));    }
}

