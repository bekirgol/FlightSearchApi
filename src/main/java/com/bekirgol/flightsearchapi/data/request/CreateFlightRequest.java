package com.bekirgol.flightsearchapi.data.request;

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
public class CreateFlightRequest {
    private long departureAirportId;
    private long arrivalAirportId;
//    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private String departureDate;
//    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private String arrivalDate;
    private double price;
}
