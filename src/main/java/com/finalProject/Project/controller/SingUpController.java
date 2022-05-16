package com.finalProject.Project.controller;

import com.finalProject.Project.entity.Customer;
import com.finalProject.Project.entity.User;
import com.finalProject.Project.entity.dto.UserDto;
import com.finalProject.Project.service.interfaces.SingUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class SingUpController {
    @Autowired
    SingUpService singUpService;
    @PostMapping
    public void singUpCustomer(@RequestBody UserDto customer){
        System.out.println(customer);
    }

}
