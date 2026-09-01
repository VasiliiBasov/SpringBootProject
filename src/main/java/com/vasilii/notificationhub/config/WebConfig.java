package com.vasilii.notificationhub.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/h2-console/**")
                .setViewName("forward:/h2-console");
        registry.addViewController("/h2-console")
                .setViewName("forward:/h2-console");
        registry.addViewController("/h2-console/")
                .setViewName("forward:/h2-console");
    }
}
