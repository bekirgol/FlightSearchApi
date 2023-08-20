package com.bekirgol.flightsearchapi.repository;

import com.bekirgol.flightsearchapi.data.entity.Airport;
import com.bekirgol.flightsearchapi.data.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByDepartureAirportIdAndArrivalAirportIdAndDepartureDateGreaterThanEqualAndArrivalDateGreaterThanEqual(Long departureAirport, Long arrivalAirport, Date departureDate, Date arrivalDate);
    List<Flight> findByDepartureAirportIdAndArrivalAirportIdAndDepartureDateGreaterThanEqual(Long departureAirport, Long arrivalAirport, Date departureDate);
}
