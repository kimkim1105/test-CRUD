package com.example.demospring;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@SpringBootApplication
//@Controller
public class DemospringApplication {
//    @RequestMapping(value = "/index")
//    public String index() {
//        System.out.println("ccc");
//        return "index";
//    }
//    @Bean
//    public ModelMapper modelMapper() {
//        return new ModelMapper();
//    }
    public static void main(String[] args) {
        SpringApplication.run(DemospringApplication.class, args);
    }


}
