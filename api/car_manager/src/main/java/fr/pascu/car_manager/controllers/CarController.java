package fr.pascu.car_manager.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.pascu.car_manager.exceptions.CarNotFoundException;
import fr.pascu.car_manager.exceptions.DifferentIdException;
import fr.pascu.car_manager.models.Brands;
import fr.pascu.car_manager.models.Car;
import fr.pascu.car_manager.models.CarDTO;
import fr.pascu.car_manager.models.CarDTOMapper;
import fr.pascu.car_manager.repositories.CarRepository;

@RestController
@CrossOrigin()
public class CarController {
    @Autowired
    private CarRepository repository;
    private final CarDTOMapper mapper = Mappers.getMapper(CarDTOMapper.class);

    @GetMapping("/cars")
    ResponseEntity<List<CarDTO>> all(){
        return new ResponseEntity<>(
            this.repository.findAll().stream().map(mapper::carToDTO)
            .collect(Collectors.toList()),
            HttpStatus.OK
        );
    }    

    @GetMapping("/cars/{id}")
    ResponseEntity<CarDTO> one(@PathVariable String id){
        CarDTO car = this.mapper.carToDTO(this.repository.findById(id)
        .orElseThrow(
            () -> new CarNotFoundException(id)
        ));
        return new ResponseEntity<CarDTO>(car, HttpStatus.OK);
    }

    @GetMapping("/cars/brands")
    ResponseEntity<Brands[]> getBrands(){
        return new ResponseEntity<Brands[]>(Brands.values(), HttpStatus.OK);    
    }

    @PostMapping("/cars")
    ResponseEntity<CarDTO> newCar(@RequestBody CarDTO carDTO){
        Car newCar = mapper.DTOToCar(carDTO);
        return new ResponseEntity<CarDTO>(mapper.carToDTO(this.repository.save(newCar)), HttpStatus.CREATED); 
    }

    @PutMapping("/cars/{id}")
    ResponseEntity<CarDTO> replaceCar(@RequestBody CarDTO newCar, @PathVariable String id){
        if(!id.equals(newCar.getId())){
            throw new DifferentIdException(id, newCar.getId());
        }
        Car oldCar = this.repository.findById(id)
            .orElseThrow(
                () -> new CarNotFoundException(id)
            ); 
        oldCar = mapper.DTOToCar(newCar);
        return new ResponseEntity<CarDTO>(mapper.carToDTO(this.repository.save(oldCar)), HttpStatus.OK);
    }

    @DeleteMapping("/cars/{id}")
    ResponseEntity<HttpStatus> deleteCar(@PathVariable String id){
        Car deletedCar = this.repository.findById(id)
            .orElseThrow(() -> new CarNotFoundException(id));
        this.repository.delete(deletedCar);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }
}
