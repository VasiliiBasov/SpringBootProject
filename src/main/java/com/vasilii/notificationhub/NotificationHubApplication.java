package com.vasilii.notificationhub;

import com.vasilii.notificationhub.service.GreetingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class NotificationHubApplication {

	public static void main(String[] args) {

        ConfigurableApplicationContext ctx = SpringApplication.run(NotificationHubApplication.class, args);
        GreetingService gr = ctx.getBean(GreetingService.class);
        gr.greet();
        ctx.close();
	}

}
