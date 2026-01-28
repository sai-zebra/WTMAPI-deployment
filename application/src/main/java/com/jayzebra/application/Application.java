package com.jayzebra.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/**
 * Main application class.
 * The annotations here are critical for a multi-module project.
 */

@SpringBootApplication(scanBasePackages = "com.jayzebra")

@EntityScan(basePackages = {
        "com.jayzebra.feedsmodule.adapter.out.entity",
        "com.jayzebra.surveys.adapter.output.entity",
        "com.jayzebra.rtm.adapter.out.entity",
        "com.jayzebra.usermodule.adapter.out.entity"
})

// Similarly, you MUST explicitly tell Spring Data JPA where to find your Repository interfaces.
@EnableJpaRepositories(basePackages = {
        "com.jayzebra.feedsmodule.adapter.out.repository",
        "com.jayzebra.surveys.adapter.output.repository",
        "com.jayzebra.rtm.adapter.out.repository",
        "com.jayzebra.usermodule.adapter.out.repository"
})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
