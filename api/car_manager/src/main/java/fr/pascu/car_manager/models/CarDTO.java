package fr.pascu.car_manager.models;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@NoArgsConstructor
public class CarDTO {
    private String id;
    private Brand brand;
    private String model;
    private String driverName;
    private String registration;
    private Date circulationDate;
    private Double estimatedPrice;
}
