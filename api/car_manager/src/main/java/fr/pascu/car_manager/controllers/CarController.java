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

import fr.pascu.car_manager.exceptions.DifferentIdException;
import fr.pascu.car_manager.models.Brands;
import fr.pascu.car_manager.models.Car;
import fr.pascu.car_manager.models.CarDTO;
import fr.pascu.car_manager.models.CarDTOMapper;
import fr.pascu.car_manager.repositories.CarRepository;
import fr.pascu.car_manager.services.CarService;

@RestController
@CrossOrigin()
public class CarController {
    @Autowired
    private CarService carService;
    private final CarDTOMapper mapper = Mappers.getMapper(CarDTOMapper.class);

    @GetMapping("/cars")
    ResponseEntity<List<CarDTO>> all(){
        return new ResponseEntity<>(
            this.carService.findAllCars().stream().map(mapper::carToDTO)
            .collect(Collectors.toList()),
            HttpStatus.OK
        );
    }    

    @GetMapping("/cars/{id}")
    ResponseEntity<CarDTO> one(@PathVariable String id){
        CarDTO car = this.mapper.carToDTO(this.carService.findCarById(id));
        return new ResponseEntity<CarDTO>(car, HttpStatus.OK);
    }

    @GetMapping("/cars/brands")
    ResponseEntity<Brands[]> getBrands(){
        return new ResponseEntity<Brands[]>(
            this.carService.getBrands(), 
            HttpStatus.OK
        );    
    }

    @PostMapping("/cars")
    ResponseEntity<CarDTO> newCar(@RequestBody CarDTO carDTO){
        Car newCar = mapper.DTOToCar(carDTO);
        return new ResponseEntity<CarDTO>(
            mapper.carToDTO(this.carService.addNewCar(newCar)), 
            HttpStatus.CREATED
        ); 
    }

    @PutMapping("/cars/{id}")
    ResponseEntity<CarDTO> replaceCar(@RequestBody CarDTO newCarDTO, @PathVariable String id){
        if(!id.equals(newCarDTO.getId())){
            throw new DifferentIdException(id, newCarDTO.getId());
        }
        Car newCar = mapper.DTOToCar(newCarDTO);
        return new ResponseEntity<CarDTO>(
            mapper.carToDTO(this.carService.replaceCar(id, newCar)), 
            HttpStatus.OK
        );
    }

    @DeleteMapping("/cars/{id}")
    ResponseEntity<HttpStatus> deleteCar(@PathVariable String id){
        this.carService.deleteCarById(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }
}
