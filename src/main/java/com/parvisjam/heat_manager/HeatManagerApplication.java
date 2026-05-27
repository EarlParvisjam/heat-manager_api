package com.parvisjam.heat_manager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.parvisjam.heat_manager.service.UserService;

@SpringBootApplication
public class HeatManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeatManagerApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			UserService userService = ctx.getBean(UserService.class);
			userService.fetchUser();
			System.out.println("Hello World");
		};
	}
}
