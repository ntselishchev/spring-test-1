package com.ntselishchev.springapptest;

import com.ntselishchev.springapptest.controller.TestController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringTestApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringTestApplication.class, args);
		TestController service = context.getBean(TestController.class);
		service.processTest();
	}

}
