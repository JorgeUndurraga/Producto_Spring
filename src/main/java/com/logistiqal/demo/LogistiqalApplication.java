package com.logistiqal.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
//import org.springframework.boot.web.support.SpringBootServletInitializer;


import com.logistiqal.demo.LogistiqalApplication;

@SpringBootApplication
public class LogistiqalApplication extends SpringBootServletInitializer{
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(LogistiqalApplication.class);
    }

	public static void main(String[] args) {
		SpringApplication.run(LogistiqalApplication.class, args);
	}

}
