package com.finalProject.Project;

import com.finalProject.Project.app.SingUpApp;
import com.finalProject.Project.entity.Customer;
import com.finalProject.Project.service.interfaces.SingUpService;
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
		SingUpApp sing;

		public Start(SingUpApp sing) {
			this.sing = sing;
		}

		@Override
		public void run(ApplicationArguments args) throws Exception {
			Customer customer = new Customer(null,null,"majid2","majid","majaaapiuoid","nnnn",null);
			sing.singUpForCustomer();
			sing.requestForSingUp();
		}
	}

}
