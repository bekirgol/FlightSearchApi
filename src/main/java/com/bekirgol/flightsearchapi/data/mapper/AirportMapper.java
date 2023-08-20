package com.bekirgol.flightsearchapi.data.mapper;

import com.bekirgol.flightsearchapi.data.dto.AirportDto;
import com.bekirgol.flightsearchapi.data.entity.Airport;
import com.bekirgol.flightsearchapi.data.request.CreateAirportRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AirportMapper {
    AirportMapper AIRPORT_MAPPER = Mappers.getMapper(AirportMapper.class);
    Airport createAirport(CreateAirportRequest createAirportRequest);
    AirportDto convertAirportDto(Airport airport);
}
