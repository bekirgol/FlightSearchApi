package com.bekirgol.flightsearchapi.service;

import com.bekirgol.flightsearchapi.data.dto.AirportDto;
import com.bekirgol.flightsearchapi.data.entity.Airport;
import com.bekirgol.flightsearchapi.data.request.CreateAirportRequest;

import java.util.List;

public interface AirportService {
    AirportDto createAirport(CreateAirportRequest createAirportRequest);

    List<AirportDto> listAllAirport();

    AirportDto listById(long airportId);

    AirportDto updateAirport(long airportId, CreateAirportRequest createAirportRequest);

    void deleteAirport(long airportId);
}
