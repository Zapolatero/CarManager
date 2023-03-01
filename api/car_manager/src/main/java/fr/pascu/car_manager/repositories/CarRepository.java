package fr.pascu.car_manager.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import fr.pascu.car_manager.models.Car;

public interface CarRepository extends MongoRepository<Car, String> {
    
}
