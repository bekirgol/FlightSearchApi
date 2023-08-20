package com.bekirgol.flightsearchapi.service.impl;

import com.bekirgol.flightsearchapi.data.dto.AirportDto;
import com.bekirgol.flightsearchapi.data.entity.Airport;
import com.bekirgol.flightsearchapi.data.request.CreateAirportRequest;
import com.bekirgol.flightsearchapi.repository.AirportRepository;
import com.bekirgol.flightsearchapi.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import static com.bekirgol.flightsearchapi.data.mapper.AirportMapper.AIRPORT_MAPPER;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultAirportService implements AirportService {

    private final AirportRepository airportRepository;
    @Override
    public AirportDto createAirport(CreateAirportRequest createAirportRequest) {
        Airport airport = AIRPORT_MAPPER.createAirport(createAirportRequest);
        return AIRPORT_MAPPER.convertAirportDto(airportRepository.save(airport));
    }

    @Override
    public List<AirportDto> listAllAirport() {
        List<Airport> airports = airportRepository.findAll();
        List<AirportDto> airportDtoList= new ArrayList<>();
        for(Airport airport: airports) {
            airportDtoList.add(AIRPORT_MAPPER.convertAirportDto(airport));
        }
        return airportDtoList;
    }

    @Override
    public AirportDto listById(long airportId) {
        Airport airport = airportRepository.findById(airportId).orElseThrow(() -> new NotFoundException("Airport Not found"));
        return AIRPORT_MAPPER.convertAirportDto(airport);
    }


    @Override
    public AirportDto updateAirport(long airportId, CreateAirportRequest createAirportRequest) {
        Airport airport = airportRepository.findById(airportId).orElseThrow(() -> new NotFoundException("Airport not found"));
        airport.setCity(createAirportRequest.getCity());
        return AIRPORT_MAPPER.convertAirportDto(airportRepository.save(airport));
    }

    @Override
    public void deleteAirport(long airportId) {
        airportRepository.deleteById(airportId);
    }
}
