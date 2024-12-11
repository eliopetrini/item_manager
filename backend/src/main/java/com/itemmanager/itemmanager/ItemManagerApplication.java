package com.itemmanager.itemmanager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class ItemManagerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ItemManagerApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ItemManagerApplication.class);
    }

    @Bean
    CommandLineRunner init(StoredItemsRepository storedItemsRepository, ChangeLogRepository changeLogRepository, LocationRepository locationRepository) {
        return args -> {
            Location location = new Location("asd", "ciao");
            //locationRepository.save(location);
            StoredItems storedItems = new StoredItems("Name", "Description", new Date(), new Date(), 1, location);
            //storedItemsRepository.save(storedItems);
            storedItemsRepository.findAll().forEach(System.out::println);
        };
    }

}
