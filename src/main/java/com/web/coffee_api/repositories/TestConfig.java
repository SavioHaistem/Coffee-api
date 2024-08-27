package com.web.coffee_api.repositories;
import com.web.coffee_api.entities.*;
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
    @Autowired
    private CupSizeRepository cupSizeRepository;
    @Autowired
    private CoffeeCupRepository coffeeCupRepository;

    @Override
    public void run(String... args) throws Exception {
        CupSize s1 = new CupSize(null, Size.SMALL);
        CupSize s2 = new CupSize(null, Size.MEDIUM);
        CupSize s3 = new CupSize(null, Size.BIG);
        cupSizeRepository.saveAll(Arrays.asList(s1,s2,s3));

        Coffee cf1 = new Coffee(null,"Lunatic Coffee","A Lunar Coffee of moon light",20.0);
        Coffee cf2 = new Coffee(null,"Earth Coffee","Made by Humans in Earth",2.0);
        Coffee cf3 = new Coffee(null,"Solar Coffee","Hot coffee made by sunlight",12.0);
        Coffee cf4 = new Coffee(null,"Math Coffee","The more smart coffee of earth",8.0);
        coffeeRepository.saveAll(Arrays.asList(cf1,cf2,cf3,cf4));

        Cup cu1 = new Cup(null,"Lunar cup");
        Cup cu2 = new Cup(null,"Porcelain cup");
        Cup cu3 = new Cup(null,"Glass cup");
        cupRepository.saveAll(Arrays.asList(cu1,cu2,cu3));

        cu1.getSizes().addAll(Arrays.asList(s1,s2));
        cu2.getSizes().addAll(Arrays.asList(s2,s3));
        cu3.getSizes().addAll(Arrays.asList(s1,s2,s3));
        cupRepository.saveAll(Arrays.asList(cu1,cu2,cu3));

        CoffeeCup cfcu1 = new CoffeeCup(null,cf1,cu1,s1);
        CoffeeCup cfcu2 = new CoffeeCup(null,cf1,cu1,s2);
        coffeeCupRepository.saveAll(Arrays.asList(cfcu1,cfcu2));
    }
}
