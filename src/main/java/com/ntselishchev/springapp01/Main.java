package com.ntselishchev.springapp01;

import com.ntselishchev.springapp01.controller.TestController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class Main {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        TestController service = context.getBean(TestController.class);
        service.processTest();
    }
}
