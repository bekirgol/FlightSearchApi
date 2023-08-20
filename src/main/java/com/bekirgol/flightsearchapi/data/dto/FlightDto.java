package com.bekirgol.flightsearchapi.data.dto;

import com.bekirgol.flightsearchapi.data.entity.Airport;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightDto {
    private String id;
    private Airport departureAirport;
    private Airport arrivalAirport;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date departureDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date arrivalDate;
    private double price;
}
