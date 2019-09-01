package com.rantsroom;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.rantsroom.controller.UserProfileController;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan(basePackages = "com.rantsroom")
public class WebApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebApplication.class);
    }

    public static void main(String[] args) throws Exception {
    	new File(UserProfileController.UPLOADED_FOLDER).mkdir();
        SpringApplication.run(WebApplication.class, args);
    }
}