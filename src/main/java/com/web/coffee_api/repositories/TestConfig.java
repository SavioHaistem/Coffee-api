package com.web.coffee_api.repositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("h2")
public class TestConfig implements CommandLineRunner {
    @Autowired
    private CoffeeRepository coffeeRepository;
    @Autowired
    private CupSizeRepository cupSizeRepository;
    @Autowired
    private CupRepository cupRepository;

    @Override
    public void run(String... args) throws Exception {

    }
}
