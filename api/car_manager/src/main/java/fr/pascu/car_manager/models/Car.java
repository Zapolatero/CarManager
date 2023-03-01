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
}
