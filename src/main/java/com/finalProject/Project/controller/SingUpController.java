package com.finalProject.Project.controller;

import com.finalProject.Project.entity.Customer;
import com.finalProject.Project.entity.User;
import com.finalProject.Project.entity.dto.UserDto;
import com.finalProject.Project.service.interfaces.SingUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/s")
public class SingUpController {
    @Autowired
    SingUpService singUpService;
    private Checker checker = new Checker();
    @PostMapping("/customer")
    public void singUpCustomer(@ModelAttribute UserDto userDto){
        checker.checkerForSingUp(userDto);
        singUpService.insertCustomer(userDto);
        System.out.println(userDto);
    }
    @PostMapping("/expert")
    public void singUpExpert(@ModelAttribute UserDto  userDto) throws IOException {
        System.out.println(userDto.getFirstName());
//        System.out.println(userDto.getImage().length);
        System.out.println(userDto.getImage().getSize());
        checker.checkerForSingUp(userDto);
        checker.checkSizeOfAvatar(userDto.getImage());
        singUpService.requestForSingUp(userDto);
    }

}
