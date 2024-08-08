package com.web.coffee_api.repositories;
import com.web.coffee_api.entities.Coffee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("dev")
public class TestConfig implements CommandLineRunner {
    @Autowired
    private CoffeeRepository coffeeRepository;

    @Override
    public void run(String... args) throws Exception {
        Coffee cf1 = new Coffee(null,"Lunatic Coffee","A Lunarian Coffee of moon light",1.0,20.0);
        Coffee cf2 = new Coffee(null,"Earth Coffee","Made by Humans in Earth",1.0,2.0);
        Coffee cf3 = new Coffee(null,"Solar Coffee","Hot coffee made by sunlight",1.0,12.0);
        coffeeRepository.saveAll(Arrays.asList(cf1,cf2,cf3));
    }
}
