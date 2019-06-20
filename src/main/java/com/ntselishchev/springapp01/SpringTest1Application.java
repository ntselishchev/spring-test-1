package com.ntselishchev.springapp01;

import com.ntselishchev.springapp01.controller.TestController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringTest1Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringTest1Application.class, args);
		TestController service = context.getBean(TestController.class);
		service.processTest();
	}

}
