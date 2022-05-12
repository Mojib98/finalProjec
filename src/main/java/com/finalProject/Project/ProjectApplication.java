package com.finalProject.Project;

import com.finalProject.Project.app.*;
import com.finalProject.Project.app.SingUpAppImpl;
import com.finalProject.Project.entity.Customer;
import com.finalProject.Project.entity.Expert;
import com.finalProject.Project.service.imp.ManageExpertService;
import com.finalProject.Project.service.imp.OfferServiceImpl;
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
		@Autowired
		ManageExpertService expertService;
		@Autowired
		OfferServiceImpl offerService;

		Expert expert = new Expert();
		Customer customer = new Customer();


		@Override
		public void run(ApplicationArguments args) throws Exception {
			expert.setId(17);
			expertApp.setExpert(expert);
			customer.setId(13);
			customerApp.setCustomer(customer);
	/*		customerApp.choiceOffer();
			expertApp.writeOffer();*/
//			while (true)
//				expertApp.writeOffer();
//				customerApp.choiceOffer();
//			expertApp.downWork();

//
//		var s=	offerService.sortByRateAndPrice(13);
//			System.out.println(s);
//			menu.MangeSpecialist();

		}
	}

}
