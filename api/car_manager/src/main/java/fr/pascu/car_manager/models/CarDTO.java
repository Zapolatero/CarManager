package fr.pascu.car_manager.models;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@NoArgsConstructor
public class CarDTO {
    private String id;
    @NotNull(message = "Brand cannot be null")
    private Brands brand;
    @NotNull(message = "Model cannot be null")
    @Size(min = 2, message = "Model must be at least 2 characters")
    private String model;
    @NotNull(message = "Driver name cannot be null")
    @Size(min = 2, message = "Driver name must be at least 2 characters")
    private String driverName;
    @NotNull(message = "Registration cannot be null")
    @Size(min = 5, message = "Registration must be at least 5 characters")
    private String registration;
    @NotNull(message = "Circulation date cannot be null")
    private Date circulationDate;
    @NotNull(message = "Estimated price cannot be null")
    @Positive( message = "Estimated price cannot be 0 or below")
    private Double estimatedPrice;
}
