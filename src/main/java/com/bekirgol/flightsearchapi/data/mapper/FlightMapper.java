package com.bekirgol.flightsearchapi.data.mapper;


import com.bekirgol.flightsearchapi.data.dto.FlightDto;
import com.bekirgol.flightsearchapi.data.entity.Airport;
import com.bekirgol.flightsearchapi.data.entity.Flight;
import com.bekirgol.flightsearchapi.data.request.CreateFlightRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FlightMapper extends DateMapper {

    FlightMapper FLIGHT_MAPPER = Mappers.getMapper(FlightMapper.class);

    @Mapping(source = "departureAirportId", target = "departureAirport")
    @Mapping(source = "arrivalAirportId", target = "arrivalAirport")
    Flight createFlight(CreateFlightRequest createFlightRequest);

    FlightDto convertFlightDto(Flight flight);

    Airport toObjectAirport(Long id);
}
