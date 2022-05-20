package com.finalProject.Project.service.imp;

import com.finalProject.Project.entity.Avatar;
import com.finalProject.Project.entity.Customer;
import com.finalProject.Project.entity.Expert;
import com.finalProject.Project.entity.SubService;
import com.finalProject.Project.entity.dto.ServiceDto;
import com.finalProject.Project.entity.dto.UserDto;
import com.finalProject.Project.entity.enumeration.UserStatus;
import com.finalProject.Project.repository.interfaces.SingUpRepository;
import com.finalProject.Project.service.interfaces.SingUpService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
public class SingUpServiceImp  implements SingUpService {
   private final SingUpRepository singUpRepository;
   private final ModelMapper modelMapper = new ModelMapper();

    public SingUpServiceImp(SingUpRepository singUpRepository) {
        this.singUpRepository = singUpRepository;
    }


    @Override
    public void requestForSingUp(UserDto userDto) throws IOException {
   /*     modelMapper.addMappings(new PropertyMap<UserDto, Expert>() {
            @Override
            protected void configure() {
//                skip(destination.getAvatar());
                skip(source.getImage());
            }
        });*/
        Expert expert = modelMapper.map(userDto, Expert.class);
        expert.setStatus(UserStatus.AWAITING_CONFIRMATION);
        expert.setBudget(0);
        expert.setRate(5);
        expert.setAvatar(new Avatar(userDto.getImage().getBytes()));
        singUpRepository.save(expert);
    }

    @Override
    @Transactional
    public void insertCustomer(UserDto userDto) {
        Customer customer = modelMapper.map(userDto, Customer.class);
        customer.setStatus(UserStatus.ACTIVE);
        customer.setBudget(50000);
        singUpRepository.save(customer);
    }
}
