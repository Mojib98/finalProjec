package com.finalProject.Project.controller;

import com.finalProject.Project.entity.dto.UserDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Pattern;

public class Checker {
    public  void  checkEmail(String email){
        String regexPattern="^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
                if (Pattern.compile(regexPattern)
                        .matcher(email)
                        .matches()) {
                } else
                    throw new RuntimeException("invalid email");
        }
    public void checkSizeOfAvatar(MultipartFile avatar){
        if (avatar.isEmpty())
            throw new RuntimeException("please insert photo");
        if (avatar.getSize()>299000)
                   throw new RuntimeException("size image too big");

        }
        public void checkPassword(String password){
            String regexPattern="^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$";
            if (Pattern.compile(regexPattern)
                    .matcher(password)
                    .matches()) {
            } else
                throw new RuntimeException("invalid password");
        }
        public void checkerForSingUp(UserDto userDto){
            checkPassword(userDto.getPassword());
            checkEmail(userDto.getEmail());
        }


    }

