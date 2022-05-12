package com.finalProject.Project;

import com.finalProject.Project.app.*;
import com.finalProject.Project.app.impl.SingUpAppImpl;
import com.finalProject.Project.entity.Customer;
import com.finalProject.Project.entity.Expert;
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
		CustomerApp customerApp;
		@Autowired
		Menu menu;

		Expert expert = new Expert();
		Customer customer = new Customer();


		@Override
		public void run(ApplicationArguments args) throws Exception {
		/*	expert.setId(17);
			expertApp.setExpert(expert);
			customer.setId(12);
			customerApp.setCustomer(customer);
//			expertApp.writeOffer();
			customerApp.choiceOffer();*/
			while (true)
			menu.MangeSpecialist();

//			while (true)
//				expertApp.writeOffer();
		}
	}

}
