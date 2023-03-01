package fr.pascu.car_manager.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import fr.pascu.car_manager.exceptions.CarNotFoundException;
import fr.pascu.car_manager.exceptions.DifferentIdException;
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

    @GetMapping("/cars/{id}")
    Car one(@PathVariable String id){
        return this.repository.findById(id)
            .orElseThrow(
                () -> new CarNotFoundException(id)
            );
    }

    @PostMapping("/cars")
    Car newCar(@RequestBody Car car){
        return this.repository.save(car);
    }

    @PutMapping("/cars/{id}")
    Car replaceCar(@RequestBody Car newCar, @PathVariable String id){
        if(!id.equals(newCar.getId())){
            throw new CarNotFoundException(newCar.getId());
        }
        Car oldCar = this.repository.findById(id)
            .orElseThrow(
                () -> new CarNotFoundException(id)
            ); 
        oldCar = newCar;
        return this.repository.save(oldCar);
    }
}
