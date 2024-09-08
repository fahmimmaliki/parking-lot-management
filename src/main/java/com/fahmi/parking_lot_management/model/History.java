package com.fahmi.parking_lot_management.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String licensee;
    private LocalDateTime arrival;
    private LocalDateTime leave;
    private int bill;

    public History() {}

    public History(String licensee, LocalDateTime arrival, LocalDateTime leave, int bill) {
        this.licensee = licensee;
        this.arrival = arrival;
        this.leave = leave;
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

    public int getBill() {
        return bill;
    }

    public void setBill(int bill) {
        this.bill = bill;
    }
}
