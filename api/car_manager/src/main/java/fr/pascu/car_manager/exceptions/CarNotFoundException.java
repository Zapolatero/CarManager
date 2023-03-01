package fr.pascu.car_manager.exceptions;

public class CarNotFoundException extends RuntimeException {

    public CarNotFoundException(String id) {
        super("No car found with Id : " + id);
    }
    
}
