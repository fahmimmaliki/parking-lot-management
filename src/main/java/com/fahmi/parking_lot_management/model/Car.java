package com.fahmi.parking_lot_management.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String licensee;
    private LocalDateTime arrival;
    private LocalDateTime leave;
    private boolean isParked;
    private int bill;

    // Constructors
    public Car() {}

    public Car(String licensee, LocalDateTime arrival, LocalDateTime leave, boolean isParked, int bill) {
        this.licensee = licensee;
        this.arrival = arrival;
        this.leave = leave;
        this.isParked = isParked;
        this.bill = bill;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicensee() {
        return licensee;
    }

    public void setLicensee(String licensee) {
        this.licensee = licensee;
    }

    public LocalDateTime getArrival() {
        return arrival;
    }

    public void setArrival(LocalDateTime arrival) {
        this.arrival = arrival;
    }

    public LocalDateTime getLeave() {
        return leave;
    }

    public void setLeave(LocalDateTime leave) {
        this.leave = leave;
    }

    public boolean isParked() {
        return isParked;
    }

    public void setParked(boolean parked) {
        isParked = parked;
    }

    public int getBill() {
        return bill;
    }

    public void setBill(int bill) {
        this.bill = bill;
    }
}