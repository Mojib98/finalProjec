package com.finalProject.Project.service.interfaces;

import com.finalProject.Project.entity.Customer;
import com.finalProject.Project.entity.Expert;
import com.finalProject.Project.entity.dto.UserDto;

import java.io.IOException;

public interface SingUpService {
     void requestForSingUp(UserDto expert) throws IOException;
     void insertCustomer(UserDto customer);
     String confirmToken(String token);
}
