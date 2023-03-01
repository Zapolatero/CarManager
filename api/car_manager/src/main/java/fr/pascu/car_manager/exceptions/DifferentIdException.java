package fr.pascu.car_manager.exceptions;

public class DifferentIdException extends RuntimeException {

    public DifferentIdException(String urlId, String bodyId) {
        super("Bad request, different ids provided in url and request body : " + urlId + ", " + bodyId);
    }
    
}
