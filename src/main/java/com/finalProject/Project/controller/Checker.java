package com.finalProject.Project.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Pattern;

public class Checker {
    public  void  email(String email){
        String regexPattern="^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
                if (Pattern.compile(regexPattern)
                        .matcher(email)
                        .matches()) {
                } else
                    throw new RuntimeException("invalid email");
        }
    public void pathOfPicture(){
     /*           System.out.println("insert path of picture: ");
                File file = new File(path);
                if(file.length()>299000)
                    throw new RuntimeException("size to big");
                InputStream avatar = new FileInputStream(file);*/

        }
        public void checkPassword(String password){
        if (password.length()<8)
            throw new RuntimeException("passWord invalid");
        }


    }

