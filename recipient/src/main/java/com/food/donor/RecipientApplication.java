package com.food.donor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication(scanBasePackages = {"com.food.donor", "com.food.amqp",})
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.food.clients")
@PropertySources({@PropertySource("classpath:clients-${spring.profiles.active}.properties")})
public class RecipientApplication {
    public static void main(String[] args) {
        SpringApplication.run(RecipientApplication.class, args);
    }
}
