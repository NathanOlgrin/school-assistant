package ru.school_assistant_page;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SchoolAssistantApplicationPage
{
    public static void main( String[] args )
    {
        SpringApplication.run(SchoolAssistantApplicationPage.class);
    }
}
