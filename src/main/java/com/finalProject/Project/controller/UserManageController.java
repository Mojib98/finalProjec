package com.finalProject.Project.controller;

import com.finalProject.Project.entity.*;
import com.finalProject.Project.entity.dto.*;
import com.finalProject.Project.repository.interfaces.UserRepository;
import com.finalProject.Project.service.interfaces.ManageProfile;
import com.finalProject.Project.service.interfaces.ManageServiceForExpert;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
@AllArgsConstructor
public class UserManageController {

    private final ManageServiceForExpert manager;
    private final ManageProfile managerProfileService;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    @PostMapping("/expertdetermine")
    public void determineSingUp(@ModelAttribute UserDto selectedExpert) {
        manager.handleRequestForExpert(selectedExpert.getIds());
    }

    @PostMapping("/specialtydetermine")
    public void determineForRequestSpecialty(@ModelAttribute SpecialistDto specialistDto) {
        manager.handelRequestForSpecialty(specialistDto.getIds());
    }

    @GetMapping("/listExpert")
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<UserDto> requestListSingUp() {
        List<Expert> list = manager.requestListSingUp();
        return Arrays.asList(modelMapper.map(list, UserDto[].class));
    }

    @GetMapping("/specialty")
    public List<SpecialistDto> specialistDtoList() {
        List<Specialty> list = manager.requestListSpecialty();
        modelMapper.addMappings(new PropertyMap<Specialty, SpecialistDto>() {
            @Override
            protected void configure() {
//                skip(destination.getService());
                map().setExpertFirstName(source.getExpert().getFirstName());
                map().setExpertLastName(source.getExpert().getLastName());
                map().setServiceName(source.getService().getName());
//                skip(source.getServiceName());
            }
        });
        return  Arrays.asList(modelMapper.map(list, SpecialistDto[].class));
    }

    @PostMapping("/searchexpert")
    public List<UserDto> searchExpert(@ModelAttribute UserDto userDto) {
        System.out.println(userDto);
        Expert expert = modelMapper.map(userDto, Expert.class);
        List<Expert> experts = manager.search(expert);
        return Arrays.asList(modelMapper.map(experts, UserDto[].class));
    }

    @PostMapping("/searchcustomer")
//    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<UserDto> searchCustomer(@ModelAttribute UserDto userDto) {
        System.out.println(userDto);
        Customer customer = modelMapper.map(userDto, Customer.class);
        List<Customer> customers = manager.searchCustomer(customer);
        return Arrays.asList(modelMapper.map(customers, UserDto[].class));
    }

    @PostMapping("/addSpecialty")
    public void addSpecialty(@ModelAttribute SpecialistDto specialistDto) {

        manager.insertSpecialty(specialistDto);
    }

    /*  private Expert optionForSearch() {
          Expert expert = new Expert();
          System.out.println("\t\t!!!if want add option insert request else insert  'no'");
          System.out.println("\tfirst name");
          String fName = checker();
          expert.setFirstName(fName);
          System.out.println("\tlast name");
          String lName = checker();
          expert.setLastName(lName);
          System.out.println("\temail");
          String email = checker();
          expert.setEmail(email);
          System.out.println("\tstatus");
          String status = checker();
          UserStatus status1 = UserStatus.valueOf(status);
          expert.setStatus(status1);
          return expert;*/
//        UserStatus status1 = UserStatus.CONFIRMED;
    //  }
/*    private String checker() {
        if (string.equals("no"))
            return null;
        else
            return string;

    }*/
    public void removeExpert() {
        System.out.println("please insert number id");
//        manager.remove(id);

    }

    //    @GetMapping("/historyofcustomer")
//    public List<OfferDto> historyOfCustomer( String email){
//        var listOf = managerProfileService.historyOfferForCustomer(email);
//        return Arrays.asList(modelMapper.map(listOf, OfferDto[].class));
//    }
//    @GetMapping("/historyofexpert")
//    public List<OfferDto> historyOfExpert(@ModelAttribute String email){
//        var listOf = managerProfileService.historyOfferForExpert(email);
//        return Arrays.asList(modelMapper.map(listOf, OfferDto[].class));
//    }
    @GetMapping("w{email}")
    public List<OfferDto> expertHistoryOffer(String email) {
        System.out.println(email);
        TypeMap<Offer, OfferDto> typeMap = modelMapper.getTypeMap(Offer.class, OfferDto.class);
        if (typeMap == null) {
            modelMapper.addMappings(new PropertyMap<Offer, OfferDto>() {
                @Override
                protected void configure() {
                    map().setExpertName(source.getExpert().getLastName());
                    map().setOrderId(source.getOrders().getId());
                }
            });
        }
        List<Offer> offerList = managerProfileService.historyOfferForExpert(email);
        return Arrays.asList(modelMapper.map(offerList, OfferDto[].class));

    }

    @GetMapping("/customerHistory")
    public List<OfferDto> customerHistoryOffer(@RequestParam String email) {
        System.out.println(email);
        TypeMap<Offer, OfferDto> typeMap = modelMapper.getTypeMap(Offer.class, OfferDto.class);
        if (typeMap == null) {
            modelMapper.addMappings(new PropertyMap<Offer, OfferDto>() {
                @Override
                protected void configure() {
                    map().setExpertName(source.getExpert().getLastName());
                    map().setOrderId(source.getOrders().getId());
                }
            });
        }
        List<Offer> offerList = managerProfileService.historyOfferForCustomer(email);
        System.out.println(offerList);
        return Arrays.asList(modelMapper.map(offerList, OfferDto[].class));

    }

    @PostMapping("/searchorder")
    public List<OrderDto> searchOrder(@ModelAttribute OrderDto orderDto) {
        TypeMap<Order, OrderDto> typeMap = modelMapper.getTypeMap(Order.class, OrderDto.class);
        if (typeMap == null) {
            modelMapper.addMappings(new PropertyMap<Order, OrderDto>() {
                @Override
                protected void configure() {
//                    map().setExpertName(source.getExpert().getLastName());
//                    map().setOrderId(source.getOrders().getId());
                    map().setEmail(source.getCustomers().getEmail());
                    map().setSubServiceName(source.getSubService().getName());
                    skip(destination.getExpertName());
                    skip(destination.getCustomersName());
                }
            });
        }
        var listOrder = managerProfileService.searchOrder(orderDto);
        System.out.println(orderDto);
        return Arrays.asList(modelMapper.map(listOrder, OrderDto[].class));

//        return null;
    }

    @GetMapping("/finbuserbydate{date}")
    public List<UserDto> findUserBySingUpTime(String date) {
        var list = managerProfileService.findByDate(date);
        return Arrays.asList(modelMapper.map(list, UserDto[].class));
    }

    @GetMapping("/findcustomerordernum{number}")
    public List<UserDto> findCustomerByOrderNumb(Long number) {
        System.out.println(number.getClass());
        var list = managerProfileService.findCustomerByOrderNumber(number);
        return Arrays.asList(modelMapper.map(list, UserDto[].class));
    }

    @GetMapping("/findexpertordernum{number}")
    public List<UserDto> findExpertByOrderNumb(Long number) {
        System.out.println(number.getClass());
        var list = managerProfileService.findExpertByOrderNumber(number);
        return Arrays.asList(modelMapper.map(list, UserDto[].class));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/majid")
    public String test() {
        return "ok";
    }


}
