package fr.pascu.car_manager.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.pascu.car_manager.exceptions.CarNotFoundException;
import fr.pascu.car_manager.models.Brands;
import fr.pascu.car_manager.models.Car;
import fr.pascu.car_manager.repositories.CarRepository;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public Collection<Car> findAllCars() {
        return carRepository.findAll();
    }

    public Car findCarById(String id) {
        return this.carRepository.findById(id)
            .orElseThrow(
                () -> new CarNotFoundException(id)
            );
    }

    public Brands[] getBrands(){
        return Brands.values();
    }

    @Transactional
    public Car addNewCar(Car newCar) {
        return this.carRepository.save(newCar);
    }

    @Transactional
    public Car replaceCar(String id, Car newCar) {
        Car updatedCar = this.carRepository.findById(id)
            .orElseThrow(() -> new CarNotFoundException(id));
        updatedCar.setBrand(newCar.getBrand());
        updatedCar.setModel(newCar.getModel());
        updatedCar.setRegistration(newCar.getRegistration());
        updatedCar.setDriverName(newCar.getDriverName());
        updatedCar.setCirculationDate(newCar.getCirculationDate());
        updatedCar.setEstimatedPrice(newCar.getEstimatedPrice());
        return this.carRepository.save(newCar);
    }

    @Transactional
    public void deleteCarById(String id) {
        Car deletedCar = this.carRepository.findById(id)
            .orElseThrow(() -> new CarNotFoundException(id));
        this.carRepository.delete(deletedCar);
    }


}
