package com.finalProject.Project.controller;

import com.finalProject.Project.entity.Expert;
import com.finalProject.Project.entity.Service;
import com.finalProject.Project.entity.Specialty;
import com.finalProject.Project.entity.SubService;
import com.finalProject.Project.entity.dto.ServiceDto;
import com.finalProject.Project.entity.dto.SpecialistDto;
import com.finalProject.Project.entity.dto.UserDto;
import com.finalProject.Project.entity.enumeration.UserStatus;
import com.finalProject.Project.service.imp.ManageExpertService;
import org.apache.catalina.Server;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class userManageController {

    Scanner scanner = new Scanner(System.in);
    @Autowired
    ManageExpertService manager;
    private final ModelMapper modelMapper = new ModelMapper();



    @PostMapping("/expertdetermine")
    public void determineSingUp(@ModelAttribute UserDto selectedExpert) {
        List<Expert> list = manager.requestListSingUp();
        System.out.println(selectedExpert.getIds());
        System.out.println(selectedExpert.getUserDto());
        manager.handleRequestForExpert(selectedExpert.getIds(), null);

      /*  try {
            List<Expert> accept = new ArrayList<>();
            List<Expert> unAccept = new ArrayList<>();
            for (Expert request : list) {
                System.out.print(request.getId() + "  ");
                System.out.print(request.getFirstName() + "  ");
                System.out.print(request.getLastName() + "  ");
                System.out.print(request.getTime() + "  ");
                System.out.println("if confirmation insert 'y' or insert 'n'");
                char check = scanner.next().charAt(0);
                switch (check) {
                    case 'y':
                        accept.add(request);
                        break;
                    case 'n':
                        unAccept.add(request);
                        break;
                    default:
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    @PostMapping("/specialtydetermine")
    public void determineForRequestSpecialty(@ModelAttribute SpecialistDto specialistDto) {

            manager.handelRequestForSpecialty(specialistDto.getIds(), null);

    }
    @GetMapping("/listExpert")
    public List<UserDto> requestListSingUp(){
        List<Expert> list = manager.requestListSingUp();
        return Arrays.asList(modelMapper.map(list, UserDto[].class));
    }
    @GetMapping("/specialty")
    public List<SpecialistDto> specialistDtoList(){
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
        var s=Arrays.asList(modelMapper.map(list, SpecialistDto[].class));
        return s;
    }
    @PostMapping("/searchexpert")
    public List<UserDto> search(@ModelAttribute UserDto userDto) {
        System.out.println(userDto);
      Expert expert=  modelMapper.map(userDto,Expert.class);
            List<Expert> experts = manager.search(expert);
        var s=Arrays.asList(modelMapper.map(experts, UserDto[].class));
        return s;
    }
    @PostMapping("/addSpecialty")
    public void addSpecialty(@ModelAttribute SpecialistDto specialistDto){

        manager.insertSpecialty(specialistDto);
    }


    private Expert optionForSearch() {
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
        return expert;
//        UserStatus status1 = UserStatus.CONFIRMED;
    }

    private String checker() {
        String string = scanner.next();
        if (string.equals("no"))
            return null;
        else
            return string;

    }

    public void removeExpert() {
        System.out.println("please insert number id");
        Integer id = scanner.nextInt();
        manager.remove(id);

    }


}
