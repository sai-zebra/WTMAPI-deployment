package com.jayzebra.wtmmyworkapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.jayzebra.wtmmyworkapi",       // Scans the main app and its sub-packages (like .config)
        "com.jayzebra.feedsmodule",     // Scans the entire feedsmodule for its components
        "com.jayzebra.surveys"
})
@EntityScan(basePackages = {
        "com.jayzebra.feedsmodule.adapter.out.entity",
        "com.jayzebra.surveys.adapter.output.entity"
})
@EnableJpaRepositories(basePackages = {
        "com.jayzebra.feedsmodule.adapter.out.repository",
        "com.jayzebra.surveys.adapter.output.repository"
})
public class WtmMyWorkApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WtmMyWorkApiApplication.class, args);
    }

}
