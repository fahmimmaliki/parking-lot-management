package com.fahmi.parking_lot_management.controller;

import com.fahmi.parking_lot_management.model.Car;
import com.fahmi.parking_lot_management.model.History;
import com.fahmi.parking_lot_management.repository.CarRepository;
import com.fahmi.parking_lot_management.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/parking")
public class ParkingLotController {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private HistoryRepository historyRepository;

    private int totalPlaces = 10;
    private int payPerHour = 3000;
    private int payFirstHour = 5000;

    // Get available places
    @GetMapping("/places")
    public Map<String, Object> getAvailablePlaces() {
        long parkedCars = carRepository.count();
        Map<String, Object> response = new HashMap<>();
        response.put("availablePlaces", totalPlaces - parkedCars);
        return response;
    }

    // Park a car
    @PostMapping("/park")
    public ResponseEntity<String> parkCar(@RequestBody Car car) {
        if (car.getLicensee().length() >= 4 && carRepository.count() < totalPlaces) {
            car.setArrival(LocalDateTime.now());
            car.setParked(true);
            car.setLeave(null);
            carRepository.save(car);
            return ResponseEntity.ok("Car parked successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid registration number or parking full");
        }
    }

    // Remove a car and save history
    @PostMapping("/leave/{id}")
    public ResponseEntity<String> leaveCar(@PathVariable Long id) {
        Optional<Car> carOpt = carRepository.findById(id);
        if (carOpt.isPresent()) {
            Car car = carOpt.get();
            car.setLeave(LocalDateTime.now());
            car.setParked(false);
            int bill = calculateBill(car.getArrival(), car.getLeave());
            car.setBill(bill);
            historyRepository.save(new History(car.getLicensee(), car.getArrival(), car.getLeave(), bill));
            carRepository.delete(car);
            return ResponseEntity.ok("Car has left. Bill: " + bill);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found");
    }

    // Calculate bill based on time parked
    private int calculateBill(LocalDateTime arrival, LocalDateTime leave) {
        long hours = Duration.between(arrival, leave).toHours();
        return payFirstHour + (int) (Math.max(hours - 1, 0) * payPerHour);
    }

    // Get all cars
    @GetMapping("/cars")
    public List<Car> getCars() {
        return carRepository.findAll();
    }

    // Update total places, pay per hour, and pay for first hour
    @PatchMapping("/settings")
    public ResponseEntity<String> updateSettings(@RequestBody Map<String, Integer> request) {
        if (request.containsKey("totalPlaces")) {
            Integer newTotalPlaces = request.get("totalPlaces");
            if (newTotalPlaces != null && newTotalPlaces >= 0) {
                this.totalPlaces = newTotalPlaces;
            }
        }

        if (request.containsKey("payPerHour")) {
            Integer newPayPerHour = request.get("payPerHour");
            if (newPayPerHour != null && newPayPerHour >= 0) {
                this.payPerHour = newPayPerHour;
            }
        }

        if (request.containsKey("payFirstHour")) {
            Integer newPayFirstHour = request.get("payFirstHour");
            if (newPayFirstHour != null && newPayFirstHour >= 0) {
                this.payFirstHour = newPayFirstHour;
            }
        }

        return ResponseEntity.ok("Settings updated successfully");
    }

    // Get history
    @GetMapping("/history")
    public List<History> getHistory() {
        return historyRepository.findAll();
    }
}
