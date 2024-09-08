package com.fahmi.parking_lot_management.repository;

import com.fahmi.parking_lot_management.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
