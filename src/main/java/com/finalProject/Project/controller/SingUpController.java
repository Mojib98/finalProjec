package com.finalProject.Project.controller;

import com.finalProject.Project.entity.Customer;
import com.finalProject.Project.entity.User;
import com.finalProject.Project.entity.dto.UserDto;
import com.finalProject.Project.service.interfaces.SingUpService;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/singup")
public class SingUpController {
    @Autowired
    SingUpService singUpService;
    private Checker checker = new Checker();
    @PostMapping("/customer")
    public void singUpCustomer(@ModelAttribute UserDto userDto){
        System.out.println(userDto);
        singUpService.insertCustomer(userDto);
        System.out.println(userDto);
    }
    @PostMapping("/expert")
    public void singUpExpert(@ModelAttribute UserDto  userDto) throws IOException {
//        checker.checkerForSingUp(userDto);
        checker.checkSizeOfAvatar(userDto.getImage());
        singUpService.requestForSingUp(userDto);
    }


}
