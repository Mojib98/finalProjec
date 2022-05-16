package com.finalProject.Project.controller;

import java.util.regex.Pattern;

public class Checker {
    public  void  email(String email){
        String regexPattern="^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
                if (Pattern.compile(regexPattern)
                        .matcher(email)
                        .matches());
                else
                    throw new RuntimeException("invalid email");
        }
    }

