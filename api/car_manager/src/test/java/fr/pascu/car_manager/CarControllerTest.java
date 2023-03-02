package fr.pascu.car_manager;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.pascu.car_manager.models.Brands;
import fr.pascu.car_manager.models.Car;
import fr.pascu.car_manager.repositories.CarRepository;

@AutoConfigureMockMvc
@SpringBootTest
public class CarControllerTest {
    static final String MOCK_ID = "mock_id";

    @Autowired
    private MockMvc mockMvc;

    @Autowired 
    private ObjectMapper objectMapper;

    @MockBean
    private CarRepository carRepository;

    @Test
    void shouldCreateCar() throws JsonProcessingException, Exception{
        Car car = new Car(
            Brands.Peugeot, 
            "208", 
            "Emil Seloune", 
            "FF-289-JR", new Date(), 
            15000.0
        );
    
        mockMvc.perform(post("/cars").contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(car)))
            .andExpect(status().isCreated())
            .andDo(print());
    }

    @Test
    void shouldReturnCar() throws Exception{
        Car car = new Car(
            MOCK_ID,
            Brands.Peugeot, 
            "208", 
            "Emil Seloune", 
            "FF-289-JR", 
            new Date(), 
            15000.0
        );
        when(carRepository.findById(MOCK_ID)).thenReturn(Optional.of(car));
        mockMvc.perform(get("/cars/{id}", MOCK_ID)).andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(car.getId()))
            .andExpect(jsonPath("$.brand").value(car.getBrand().toString()))
            .andExpect(jsonPath("$.driverName").value(car.getDriverName()))
            .andExpect(jsonPath("$.estimatedPrice").value(car.getEstimatedPrice().toString()))
            .andExpect(jsonPath("$.registration").value(car.getRegistration()))
            .andExpect(jsonPath("$.reliable").doesNotExist())
            // .andExpect(jsonPath("$.circulationDate").value(car.getCirculationDate().toInstant().toString()))
            .andDo(print());
    }

    @Test
    void shouldReturnCarNotFound() throws Exception{
        shouldReturnCarNotFoundGet();
        shouldReturnCarNotFoundPut();
        shouldReturnCarNotFoundDelete();
    }

    @Test
    void shouldReturnCarNotFoundDelete() throws JsonProcessingException, Exception {
        when(carRepository.findById(MOCK_ID)).thenReturn(Optional.empty());
        doNothing().when(carRepository).delete(any(Car.class));
        mockMvc.perform(delete("/cars/{id}",MOCK_ID))
            .andExpect(status().isNotFound())
            .andDo(print());
    }

    @Test
    void shouldReturnCarNotFoundPut() throws Exception {
        Car updatedCar = new Car(
            MOCK_ID,
            Brands.Citroen, 
            "updated", 
            "Dany Reluisant", 
            "BF-900-BV", 
            new Date(), 
            150.0
        );
        when(carRepository.findById(MOCK_ID)).thenReturn(Optional.empty());
        when(carRepository.save(any(Car.class))).thenReturn(updatedCar);
        mockMvc.perform(put("/cars/{id}",MOCK_ID).contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(updatedCar)))
            .andExpect(status().isNotFound())
            .andDo(print());
    }
    
    @Test
    void shouldReturnCarNotFoundGet() throws Exception{
        when(this.carRepository.findById(CarControllerTest.MOCK_ID)).thenReturn(Optional.empty());
        mockMvc.perform(get("/cars/{id}", MOCK_ID))
        .andExpect(status().isNotFound())
        .andDo(print());
    }

    @Test
    void shouldReturnCarList() throws Exception {
        List<Car> cars = new ArrayList<>() {
            {
                add(new Car(MOCK_ID, Brands.Citroen, "C4", "Les Claypool", "YR-546-FG", new Date(), 43560.0));
                add(new Car(MOCK_ID + "2", Brands.Fiat, "Punto", "Giuseppe Bevilacqua", "TR-586-2G", new Date(), 5560.0));
                add(new Car(MOCK_ID + "3", Brands.Peugeot, "203", "Jean Pougnel", "GH-846-FD", new Date(), 12000.0));
            }
        };
        when(carRepository.findAll()).thenReturn(cars);
        mockMvc.perform(get("/cars"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.size()").value(cars.size()))
        .andDo(print());
    }

    @Test
    void shouldReturnUpdatedCar() throws Exception {
        Car oldCar = new Car(
            MOCK_ID,
            Brands.Peugeot, 
            "208", 
            "Emil Seloune", 
            "FF-289-JR", 
            new Date(), 
            15000.0
        );
        Car updatedCar = new Car(
            MOCK_ID,
            Brands.Citroen, 
            "updated", 
            "Dany Reluisant", 
            "BF-900-BV", 
            new Date(), 
            150.0
        );
        when(carRepository.findById(MOCK_ID)).thenReturn(Optional.of(oldCar));
        when(carRepository.save(any(Car.class))).thenReturn(updatedCar);

        mockMvc.perform(put("/cars/{id}", MOCK_ID)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(updatedCar)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(updatedCar.getId()))
        .andExpect(jsonPath("$.brand").value(updatedCar.getBrand().toString()))
        .andExpect(jsonPath("$.driverName").value(updatedCar.getDriverName()))
        .andExpect(jsonPath("$.estimatedPrice").value(updatedCar.getEstimatedPrice().toString()))
        .andExpect(jsonPath("$.registration").value(updatedCar.getRegistration()))
        .andExpect(jsonPath("$.reliable").doesNotExist())
        .andDo(print());
    }

    

    @Test
    void shouldReturnBadRequest() throws Exception {
        String id = "different_id";
        Car updatedCar = new Car(
            MOCK_ID,
            Brands.Citroen, 
            "updated", 
            "Dany Reluisant", 
            "BF-900-BV", 
            new Date(), 
            150.0
        );
        mockMvc.perform(put("/cars/{id}",id).contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(updatedCar)))
            .andExpect(status().isBadRequest())
            .andDo(print());
    }

    @Test
    public void shouldDeleteCar() throws Exception {
        Car car = new Car(
            MOCK_ID,
            Brands.Peugeot, 
            "208", 
            "Emil Seloune", 
            "FF-289-JR", 
            new Date(), 
            15000.0
        );
        
        when(carRepository.findById(MOCK_ID)).thenReturn(Optional.of(car));
        doNothing().when(this.carRepository).deleteById(MOCK_ID);
        mockMvc.perform(delete("/cars/{id}", MOCK_ID))
        .andExpect(status().isNoContent())
        .andDo(print());
    }
}

