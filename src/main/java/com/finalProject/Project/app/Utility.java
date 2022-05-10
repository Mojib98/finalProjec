package com.finalProject.Project.app;

import com.finalProject.Project.exception.InvalidName;
import com.finalProject.Project.exception.InvalidPassword;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
public class Utility {
    private  final Scanner input = new Scanner(System.in);

    public Integer giveIntegerInput() {
        int i;
        while (true) {
            try {
                i = input.nextInt();
                input.nextLine();
                return i;
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.print("Just enter number please:");
            }
        }
    }

    public String setName(){
        String name;
        while(true){
            System.out.print("Enter name(just alpha):");
            try {
                name = input.nextLine();
                checkName(name);
                return name;
            }catch (InvalidName except){
                System.out.println(except.getMessage());
            }
        }
    }

    private void checkName(String name){
        if(name.length() < 3 )
            throw new InvalidName("name should be more than 2 character!");

    }
    public String setUserName() {
        String userName;
        while (true) {
            while (true) {
                System.out.print("Enter UserName:");
                userName = input.nextLine();
                if (userName.length() > 2)
                    break;
                else
                    System.out.println("UserName must bigger than 2!");
            }


            return userName;
        }
    }


    public String setPassword(){
        String password;
        while(true) {
            System.out.print("Enter your password:");
            try {
                password = input.nextLine();
                passwordCheck(password);
                return password;
            } catch (InvalidPassword except) {
                System.out.println(except.getMessage());
            }
        }
    }

    public void passwordCheck(String password){
        if(password.length() < 3 )
            throw new InvalidPassword("password should be more than 8 ");


    }
    public  String email(){
        String regexPattern="^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

            while (true) {
                try {
                    System.out.print("Enter email");
                    String emailAddress = input.next();
                if (Pattern.compile(regexPattern)
                        .matcher(emailAddress)
                        .matches())
                return emailAddress;
                else
                    throw new RuntimeException("invalid email");

            }catch(RuntimeException e){
                    System.out.println(e.getMessage());
                continue;
            }
        }
    }
    public InputStream pathOfPicture(){
        String path;
        while (true){
        try {
            System.out.println("insert path of picture: ");
            path = input.next();
            File file = new File(path);
            if(file.length()>299000)
                throw new RuntimeException("size to big");
            InputStream avatar = new FileInputStream(file);
            return avatar;


        }catch (IOException | RuntimeException e){
                System.out.println(e.getMessage());

        }
        }
    }
    public LocalDateTime dateTime() {
        while (true) {
            System.out.println("Year:");
            int year = input.nextInt();

            System.out.println("Date-time in format: dd.MM. HH:mm");
            input.nextLine();
            input.findInLine("(\\d\\d)\\.(\\d\\d)\\. (\\d\\d):(\\d\\d)");
            try {
                MatchResult mr = input.match();
                int month = Integer.parseInt(mr.group(2));
                int day = Integer.parseInt(mr.group(1));
                int hour = Integer.parseInt(mr.group(3));
                int minute = Integer.parseInt(mr.group(4));
                LocalDateTime dt = LocalDateTime.of(year, month, day, hour, minute);
                return dt;
            } catch (IllegalStateException e) {
                System.err.println("Invalid date-time format.");
            }

        }
    }

}

