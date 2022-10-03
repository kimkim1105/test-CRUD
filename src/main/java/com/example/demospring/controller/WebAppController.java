//package com.example.demospring.controller;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ViewResolver;
//import org.springframework.web.servlet.config.annotation.*;
//import org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter;
//import org.springframework.web.servlet.resource.PathResourceResolver;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;
//
//@Configuration
//@EnableWebMvc
//@ComponentScan("com.example.demospring")
//public class WebAppController implements WebMvcConfigurer {
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("index");
//    }
//
//    @Bean
//    public ViewResolver getViewResolver() {
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setPrefix("/templates/");
//        resolver.setSuffix(".html");
//        return resolver;
//    }
//    @Override
//    public void configureDefaultServletHandling(
//            DefaultServletHandlerConfigurer configurer) {
//        configurer.enable();
//    }
//
//    @Bean
//    public SimpleControllerHandlerAdapter simpleControllerHandlerAdapter() {
//        return new SimpleControllerHandlerAdapter();
//    }
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/resources/**")
//                .addResourceLocations("/resources/")
//                .resourceChain(true).addResolver(new PathResourceResolver());
//    }
//}
