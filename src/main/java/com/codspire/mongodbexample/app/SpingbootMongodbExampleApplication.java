package com.codspire.mongodbexample.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.codspire.mongodbexample"})
@EnableMongoRepositories("com.codspire.mongodbexample.repository")
public class SpingbootMongodbExampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpingbootMongodbExampleApplication.class, args);
    }
}
