package com.bekirgol.flightsearchapi.service;

import com.bekirgol.flightsearchapi.data.dto.FlightDto;
import com.bekirgol.flightsearchapi.data.entity.Flight;
import com.bekirgol.flightsearchapi.data.request.CreateFlightRequest;
import com.bekirgol.flightsearchapi.data.response.FlightsResponse;

import java.util.Date;
import java.util.List;

public interface FlightService {
    FlightDto createFlight(CreateFlightRequest createFlightRequest);

    List<FlightDto> listAllFlight();

    FlightDto listById(long flightId);

    FlightDto updateFlight(long flightId, CreateFlightRequest createFlightRequest);

    void deleteFlight(long flightId);

    List<FlightsResponse> getFlights(String startDate, String endDate) throws com.gol.bekir.authendicationserver.exception.BadRequestException;

    List<FlightDto> search(Long departureAirport, Long arrivalAirport, Date departureDate, Date arrivalDate);
}
