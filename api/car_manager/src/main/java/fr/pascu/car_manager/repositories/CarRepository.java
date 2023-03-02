package fr.pascu.car_manager.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import fr.pascu.car_manager.models.Car;

@Repository
public interface CarRepository extends MongoRepository<Car, String> {
    
}
