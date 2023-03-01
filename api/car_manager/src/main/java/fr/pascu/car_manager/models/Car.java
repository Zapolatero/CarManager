package fr.pascu.car_manager.models;

import java.util.Date;

import org.springframework.data.annotation.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor
@ToString @EqualsAndHashCode
public class Car {
    @Id
    private String id;
    private Brand brand;
    private String model;
    private String driverName;
    private String registration;
    private Date circulationDate;
    private Double estimatedPrice;
    
    public Car(Brand brand, String model, String driverName, String registration, Date circulationDate,
            Double estimatedPrice) {
        this.brand = brand;
        this.model = model;
        this.driverName = driverName;
        this.registration = registration;
        this.circulationDate = circulationDate;
        this.estimatedPrice = estimatedPrice;
    }

    
}
