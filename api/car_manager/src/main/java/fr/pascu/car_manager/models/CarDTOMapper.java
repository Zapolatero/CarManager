package fr.pascu.car_manager.models;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarDTOMapper {
    final String DRIVER_NAME_ADD = "java(car.getDriverFirstname() + \" \" + car.getDriverLastname())";
    final String DRIVER_FIRST_NAME = "java(driveFirstName.split(\" \")[0])";
    final String DRIVER_LAST_NAME = "java(driveFirstName.split(\" \")[1])";

    CarDTO carToDTO(Car car);
    Car DTOToCar(CarDTO dto);
}
