package com.example.config;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.example.repository.UserRespository;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
@EnableMongoRepositories(basePackageClasses = UserRespository.class)
public class UserRepositoryConfig extends AbstractMongoClientConfiguration {

    private final Environment environment;

    public UserRepositoryConfig(Environment environment) {
        this.environment = environment;
    }

    @SuppressWarnings("null")
    @Override
    protected String getDatabaseName() {
        return "users";
    }

    @SuppressWarnings("null")
    @Override
    protected Collection<String> getMappingBasePackages() {
        return Arrays.asList("com.example.ui.model.response");
    }

    @SuppressWarnings("null")
    @Override
    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(
                MongoClientSettings.builder()
                        .applyConnectionString(new ConnectionString(environment.getProperty("mongodb.connection.url")))
                        .build());
    }
    
}
