package com.bekirgol.flightsearchapi.repository;

import com.bekirgol.flightsearchapi.data.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {
}
