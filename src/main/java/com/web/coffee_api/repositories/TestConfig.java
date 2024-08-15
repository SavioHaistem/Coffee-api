package com.web.coffee_api.repositories;
import com.web.coffee_api.entities.Coffee;
import com.web.coffee_api.entities.Cup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("h2")
public class TestConfig implements CommandLineRunner {
    @Autowired
    private CoffeeRepository coffeeRepository;
    @Autowired
    private CupRepository cupRepository;

    @Override
    public void run(String... args) throws Exception {
        Coffee cf1 = new Coffee(null,"Lunatic Coffee","A Lunar Coffee of moon light",20.0);
        Coffee cf2 = new Coffee(null,"Earth Coffee","Made by Humans in Earth",2.0);
        Coffee cf3 = new Coffee(null,"Solar Coffee","Hot coffee made by sunlight",12.0);
        Coffee cf4 = new Coffee(null,"Math Coffee","The more smart coffee of earth",8.0);

        Cup cu1 = new Cup(null,"Lunar cup");
        Cup cu2 = new Cup(null,"Porcelain cup");
        Cup cu3 = new Cup(null,"Glass cup");

        coffeeRepository.saveAll(Arrays.asList(cf1,cf2,cf3,cf4));

        cu1.getCoffees().addAll(Arrays.asList(cf1,cf4));
        cu2.getCoffees().addAll(Arrays.asList(cf2,cf3,cf4));
        cu3.getCoffees().addAll(Arrays.asList(cf3,cf4));

        cupRepository.saveAll(Arrays.asList(cu1,cu2,cu3));
    }
}
