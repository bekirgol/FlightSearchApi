package com.bekirgol.flightsearchapi.service.impl;

import com.bekirgol.flightsearchapi.data.dto.FlightDto;
import com.bekirgol.flightsearchapi.data.entity.Airport;
import com.bekirgol.flightsearchapi.data.entity.Flight;
import com.bekirgol.flightsearchapi.data.request.CreateFlightRequest;
import com.bekirgol.flightsearchapi.data.response.FlightsResponse;
import com.bekirgol.flightsearchapi.repository.FlightRepository;
import com.bekirgol.flightsearchapi.service.FlightService;
import com.gol.bekir.authendicationserver.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.webjars.NotFoundException;

import java.util.*;

import static com.bekirgol.flightsearchapi.data.mapper.FlightMapper.FLIGHT_MAPPER;

@Service
@RequiredArgsConstructor
public class DefaultFlightService implements FlightService {

    private final FlightRepository flightRepository;
    @Value("${flight.service.base.url}")
    private String FLIGHTS_SERVICE_URL;

    private final RestTemplate restTemplate;

    @Override
    public FlightDto createFlight(CreateFlightRequest createFlightRequest) {
        Flight flight = FLIGHT_MAPPER.createFlight(createFlightRequest);
        return FLIGHT_MAPPER.convertFlightDto(flightRepository.save(flight));
    }

    @Override
    public List<FlightDto> listAllFlight() {
        List<Flight> flights = flightRepository.findAll();
        List<FlightDto> flightDtoList = new ArrayList<>();

        for (Flight flight : flights) {
            flightDtoList.add(FLIGHT_MAPPER.convertFlightDto(flight));
        }
        return flightDtoList;
    }

    @Override
    public FlightDto listById(long flightId) {
        Flight flight = flightRepository.findById(flightId).orElseThrow(() -> new NotFoundException("Flight not found"));
        return FLIGHT_MAPPER.convertFlightDto(flight);
    }

    @Override
    public FlightDto updateFlight(long flightId, CreateFlightRequest createFlightRequest) {
        Flight flight = flightRepository.findById(flightId).orElseThrow(() -> new NotFoundException("Airport not found"));
        FLIGHT_MAPPER.createFlight(createFlightRequest);
        return FLIGHT_MAPPER.convertFlightDto(flightRepository.save(flight));
    }

    @Override
    public void deleteFlight(long flightId) {
        flightRepository.deleteById(flightId);
    }

    @Override
    public List<FlightsResponse> getFlights(String startDate, String endDate) throws RuntimeException {
        String url = FLIGHTS_SERVICE_URL + "flights?startDate=" + startDate + "&endDate=" + endDate;
        try {
            FlightsResponse[] flightsResponses = restTemplate.getForObject(url, FlightsResponse[].class);
            if (flightsResponses != null) {
                return Arrays.asList(flightsResponses);
            } else {
                return Collections.emptyList();
            }
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching flights. Error: " + e.getMessage());
        }
    }

    @Override
    public List<FlightDto> search(Long departureAirport, Long arrivalAirport, Date departureDate, Date arrivalDate) {
        List<Flight> flights;
        List<FlightDto> flightDtoList = new ArrayList<>();
        if (arrivalDate != null) {
            flights = flightRepository.findByDepartureAirportIdAndArrivalAirportIdAndDepartureDateGreaterThanEqualAndArrivalDateGreaterThanEqual(departureAirport, arrivalAirport, departureDate, arrivalDate);
        } else {
            flights = flightRepository.findByDepartureAirportIdAndArrivalAirportIdAndDepartureDateGreaterThanEqual(departureAirport, arrivalAirport, departureDate);
        }

        for(Flight flight: flights) {
            flightDtoList.add(FLIGHT_MAPPER.convertFlightDto(flight));
        }

        return flightDtoList;
    }


}
