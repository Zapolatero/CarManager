package fr.pascu.car_manager.models;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CarDTOMapper {
    final String DRIVER_NAME_ADD = "java(car.getDriverFirstname() + \" \" + car.getDriverLastname())";
    final String DRIVER_FIRST_NAME = "java(driveFirstName.split(\" \")[0])";
    final String DRIVER_LAST_NAME = "java(driveFirstName.split(\" \")[1])";

    public abstract CarDTO carToDTO(Car car);
    public abstract Car DTOToCar(CarDTO dto);

    @AfterMapping
    public void setReliablity(@MappingTarget Car car){
        switch(car.getBrand()){
            case Peugeot:
            case Fiat:
                car.setReliable(true);
                break;
            default:
                car.setReliable(false);
                break;
        }
    }

}
