package com.finalProject.Project;

import com.finalProject.Project.app.*;
import com.finalProject.Project.app.SingUpAppImpl;
import com.finalProject.Project.entity.Customer;
import com.finalProject.Project.entity.Expert;
import com.finalProject.Project.entity.enumeration.UserStatus;
import com.finalProject.Project.service.imp.ManageExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class ProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}
	@Component
	class Start implements ApplicationRunner {
		@Autowired
		ManageForSystem manageForSystem;
		@Autowired
		SingUpAppImpl sing;
		@Autowired
		ManagerForExpert manager;
		@Autowired
		ExpertApp expertApp;
		@Autowired
        CustomerAppImpl customerApp;
		@Autowired
		Menu menu;
		@Autowired
		ManageExpertService expertService;

		Expert expert = new Expert();
		Customer customer = new Customer();


		@Override
		public void run(ApplicationArguments args) throws Exception {
			expert.setId(17);
			expertApp.setExpert(expert);
			customer.setId(13);
			customerApp.setCustomer(customer);
//			customerApp.choiceOffer();
//			while (true)
//				expertApp.writeOffer();
//				customerApp.choiceOffer();
//			expertApp.downWork();

				customerApp.payForOrder();
//			menu.MangeSpecialist();

		}
	}

}
