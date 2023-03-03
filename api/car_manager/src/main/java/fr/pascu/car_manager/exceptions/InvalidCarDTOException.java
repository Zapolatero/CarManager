package fr.pascu.car_manager.exceptions;

public class InvalidCarDTOException extends RuntimeException {

    public InvalidCarDTOException(String violations) {
        super(violations);
    }
    
}
