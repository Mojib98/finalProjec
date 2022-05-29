package com.finalProject.Project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}
	/*@Component
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
			expert.setId(80);
			expertApp.setExpert(expert);
			customer.setId(78);
			customerApp.setCustomer(customer);
	*//*		customerApp.choiceOffer();
			expertApp.writeOffer();*//*
//			while (true)
//				expertApp.writeOffer();
//				customerApp.choiceOffer();
//			expertApp.downWork();
//
//		var s=	offerService.sortByRateAndPrice(13);
//			System.out.println(s);
//					manageForSystem.addSubService();
//			menu.MangeSpecialist();
			while (true){
				try {
					expertApp.writeOffer();
//					sing.requestForSingUp();
//					customerApp.createOrder();
				}catch (Exception e){
					e.printStackTrace();
				}
			}

		}*/
//	}

}
