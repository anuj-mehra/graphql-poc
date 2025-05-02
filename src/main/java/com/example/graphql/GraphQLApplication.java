package com.example.graphql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.example.graphql")
@EnableJpaRepositories(basePackages = "com.example.graphql.repository")
@EntityScan("com.example.graphql.model")
public class GraphQLApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphQLApplication.class, args);
    }

}
