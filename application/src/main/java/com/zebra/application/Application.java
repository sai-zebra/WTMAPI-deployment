package com.zebra.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


//Main application class

@SpringBootApplication(scanBasePackages = "com.zebra")

@EntityScan(basePackages = {
        "com.zebra.feedsmodule.adapter.out.entity",
        "com.zebra.surveys.adapter.output.entity",
        "com.zebra.rtm.adapter.out.entity",
        "com.zebra.usermodule.adapter.out.entity"
})

// Similarly, you MUST explicitly tell Spring Data JPA where to find your Repository interfaces.
@EnableJpaRepositories(basePackages = {
        "com.zebra.feedsmodule.adapter.out.repository",
        "com.zebra.surveys.adapter.output.repository",
        "com.zebra.rtm.adapter.out.repository",
        "com.zebra.usermodule.adapter.out.repository"
})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
