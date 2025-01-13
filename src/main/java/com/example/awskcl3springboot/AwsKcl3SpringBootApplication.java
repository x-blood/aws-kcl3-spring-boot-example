package com.example.awskcl3springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import software.amazon.kinesis.coordinator.Scheduler;

@SpringBootApplication
public class AwsKcl3SpringBootApplication implements CommandLineRunner {

    @Autowired
    private Scheduler scheduler;

    public static void main(String[] args) {
        SpringApplication.run(AwsKcl3SpringBootApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        scheduler.run();
    }
}
