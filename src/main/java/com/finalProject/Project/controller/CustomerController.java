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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerServiceImpl service;
    private Customer customer;
    private final ModelMapper modelMapper = new ModelMapper();
    //'ROLE_ADMIN',
    @PostMapping("/createorder")
    public void createOrder(@ModelAttribute OrderDto orderDto) {
        customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        service.insertOrder(orderDto, customer);

    }
    @GetMapping("/myOffer")
    public List<OfferDto> findOfferForOrder(@RequestParam String id) {

        customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        TypeMap<Offer,OfferDto> typeMap = modelMapper.getTypeMap(Offer.class, OfferDto.class);
        if (typeMap == null) {
            modelMapper.addMappings(new PropertyMap<Offer, OfferDto>() {
                @Override
                protected void configure() {
//                skip(destination.getSubServiceId());
                    skip(destination.getExpertName());
                }
            });
        }
        Integer ids=Integer.parseInt(id);
        var list = service.findOfferByOrderId(ids);
        return Arrays.asList(modelMapper.map(list, OfferDto[].class));
    }
    @GetMapping("/myOrder")
    public List<OrderDto> seeMyOrder() {
        customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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
        customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (service.getBasePrice() > price)
            throw new RuntimeException("bad price");
    }
    @PostMapping("/choiceOffer")
    public void choiceOffer(@ModelAttribute OfferDto offerDto) {
        customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(offerDto);
            service.choiceOffer(offerDto.getId());
    }
    @GetMapping("/downOrder")
    public List<OrderDto> ListDownOffer() {
        customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(customer);

        TypeMap<Order,OrderDto> typeMap = modelMapper.getTypeMap(Order.class, OrderDto.class);
        List<Order> downOrder = service.myDownOrder(customer.getId());
        if (typeMap == null) {
            System.out.println(downOrder);
            modelMapper.addMappings(new PropertyMap<Order, OrderDto>() {
                @Override
                protected void configure() {
                map().setExpertName(source.getExpert());
                map().setOfferPrice(source.getOrderPrice());
                map().setServiceName(source.getSubService().getName());
                    skip(destination.getCustomersName());
                }
            });
        }
        return Arrays.asList(modelMapper.map(downOrder, OrderDto[].class));

    }
    @PostMapping("/paying")
    public void paying(@ModelAttribute OrderDto orderDto) {
            service.paying(orderDto);
    }
//    @PreAuthorize("hasAnyRole('CUSTOMER')")
    @PostMapping("/addcomment")
    public void commenting(OrderDto orderDto){
        customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        service.addComment(orderDto.getCommentText(),customer,orderDto.getId());

    }
    public void sortOfferByPrice(List<Offer> offers) {
        customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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
    public List<OrderDto> AllMyOffer() {
        customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        TypeMap<Order, OrderDto> typeMap = modelMapper.getTypeMap(Order.class, OrderDto.class);
        if (typeMap == null) {
            modelMapper.addMappings(new PropertyMap<Order, OrderDto>() {
                @Override
                protected void configure() {
                    map().setCustomersName(source.getCustomers().getFirstName());
                    map().setSubServiceName(source.getSubService().getName());
                }
            });
        }


        List<Order> order = service.findMyOrder(customer.getId());
        System.out.println(order);
        return Arrays.asList(modelMapper.map(order, OrderDto[].class));
    }
    @GetMapping("myInfo")
    public UserDto myInfo(){
        customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Customer customer = service.findMe(this.customer.getId());
        UserDto userDto = modelMapper.map(customer,UserDto.class);
        System.out.println(userDto);
        return userDto;
    }
    @PostMapping("/despositOnline")
    public void depositWallet(@ModelAttribute OrderDto orderDto){
        customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer customerId=customer.getId();
        Integer amount = orderDto.getAmount();
        //checking
        service.depositWallet(customerId,amount);
    }
    @PostMapping("/onlinePaying")
    public ResponseEntity<Object> OnlinePaying(@ModelAttribute OrderDto orderDto){
        customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(orderDto);
        depositWallet(orderDto);
        paying(orderDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping("/howMuch")
    public OfferDto findPrice(@RequestParam String id){
        Integer ids=Integer.parseInt(id);
        Integer price = service.OfferPrice(ids);
        OfferDto offerDto = new OfferDto();
        offerDto.setOfferPrice(price);
        return offerDto;


    }
//    @GetMapping("/")
/*    public List<OrderDto> OrderForComment(){
        modelMapper.addMappings(new PropertyMap<Order, OrderDto>() {
            @Override
            protected void configure() {
                map().setCustomersName(source.getCustomers().getFirstName());
                map().setSubServiceName(source.getSubService().getName());
            }
        });

        List<Order> order = service.findPaidOrder(customer.getId());
        return Arrays.asList(modelMapper.map(order, OrderDto[].class));    }*/

}

