package com.fahmi.parking_lot_management.repository;

import com.fahmi.parking_lot_management.model.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Long> {
}
