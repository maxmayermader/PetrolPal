package org.example.dealOfDay;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DealRepo extends JpaRepository<Deal, Long> {
    Deal findTopByOrderByIdDesc();
    Deal findTopByStationNameOrderByIdDesc(String name);
}
