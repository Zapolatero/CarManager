package fr.pascu.car_manager;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.pascu.car_manager.models.Brand;
import fr.pascu.car_manager.models.Car;
import fr.pascu.car_manager.repositories.CarRepository;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(CarRepository repository) {
        if (repository.findAll().isEmpty()){
            return args -> {
                log.info("Preloading " + repository.save(new Car(Brand.Peugeot, "208", "Emil Seloune", "FF-289-JR", new Date(), 15000.0)));
                log.info("Preloading " + repository.save(new Car(Brand.Fiat, "Panda 4x4", "Silvio Astorino", "BG-302-IJ", new Date(), 5400.0)));
            };
        }
        return null;
    }
}