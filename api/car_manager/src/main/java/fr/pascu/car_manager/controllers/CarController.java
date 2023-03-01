package fr.pascu.car_manager.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.pascu.car_manager.models.Car;
import fr.pascu.car_manager.repositories.CarRepository;

@RestController
public class CarController {
    private final CarRepository repository;

    public CarController(CarRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/cars")
    List<Car> all(){
        return this.repository.findAll();
    }    
}
