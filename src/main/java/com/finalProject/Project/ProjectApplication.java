package com.finalProject.Project;

import com.finalProject.Project.entity.Expert;
import com.finalProject.Project.entity.dto.OrderDto;
import com.finalProject.Project.entity.enumeration.WorkStatus;
import com.finalProject.Project.service.imp.ManagerProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableWebSecurity(debug = true)
public class ProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:63342");
			}
		};
	}
	@Component
	class Start implements ApplicationRunner {
		@Autowired
		ManagerProfileServiceImpl profileService;
		LocalDateTime start =LocalDateTime.of(2022,5,29, 0,0,0);
		LocalDateTime end =LocalDateTime.of(2022,5,29, 23,59,59);




		@Override
		public void run(ApplicationArguments args) throws Exception {
			OrderDto orderDto  = new OrderDto();
//			orderDto.setWorkStatus(WorkStatus.DONE);
//			orderDto.setSubServiceName("majidshoyee");
//			orderDto.setServiceName("washing");
//		var s=	profileService.searchOrder(orderDto);
//			var s = profileService.findBySingUpTime(start,end);
//			for (Expert e:s){
//				System.out.println(e.getId());
//			}

		/*	while (true){
				try {



				}catch (Exception e){
					e.printStackTrace();
				}
			}*/

		}
	}

}
