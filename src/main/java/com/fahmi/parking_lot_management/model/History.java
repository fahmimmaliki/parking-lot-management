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
    // ...
}
