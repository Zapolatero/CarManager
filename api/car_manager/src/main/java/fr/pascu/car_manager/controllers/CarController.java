package fr.pascu.car_manager.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.factory.Mappers;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.pascu.car_manager.exceptions.CarNotFoundException;
import fr.pascu.car_manager.models.Brand;
import fr.pascu.car_manager.models.Car;
import fr.pascu.car_manager.models.CarDTO;
import fr.pascu.car_manager.models.CarDTOMapper;
import fr.pascu.car_manager.repositories.CarRepository;

@RestController
@CrossOrigin()
public class CarController {
    private final CarRepository repository;
    private final CarDTOMapper mapper = Mappers.getMapper(CarDTOMapper.class);

    public CarController(CarRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/cars")
    List<CarDTO> all(){
        return this.repository.findAll().stream().map(mapper::carToDTO)
            .collect(Collectors.toList());
    }    

    @GetMapping("/cars/{id}")
    CarDTO one(@PathVariable String id){
        return this.mapper.carToDTO(this.repository.findById(id)
            .orElseThrow(
                () -> new CarNotFoundException(id)
            ));
    }

    @GetMapping("/cars/brands")
    Brand[] getBrands(){
        return Brand.values();    }

    @PostMapping("/cars")
    CarDTO newCar(@RequestBody CarDTO carDTO){
        Car newCar = mapper.DTOToCar(carDTO);
        return mapper.carToDTO(this.repository.save(newCar));
    }

    @PutMapping("/cars/{id}")
    CarDTO replaceCar(@RequestBody CarDTO newCar, @PathVariable String id){
        if(!id.equals(newCar.getId())){
            throw new CarNotFoundException(newCar.getId());
        }
        Car oldCar = this.repository.findById(id)
            .orElseThrow(
                () -> new CarNotFoundException(id)
            ); 
        oldCar = mapper.DTOToCar(newCar);
        return mapper.carToDTO(this.repository.save(oldCar));
    }

    @DeleteMapping("/cars/{id}")
    void deleteCar(@PathVariable String id){
        Car deletedCar = this.repository.findById(id)
        .orElseThrow(() -> new CarNotFoundException(id));
        this.repository.delete(deletedCar);
    }
}
