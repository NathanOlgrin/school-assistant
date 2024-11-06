package ru.school_assistant_eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SchoolAssistantEureka {
    public static void main(String[] args) {
        SpringApplication.run(SchoolAssistantEureka.class);
    }
}
