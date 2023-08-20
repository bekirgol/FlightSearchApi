package com.bekirgol.flightsearchapi.data.entity;


import jakarta.persistence.*;
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
@Entity(name = "flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "departure_airport_id")
    private Airport departureAirport;
    @ManyToOne
    @JoinColumn(name = "arrival_airport_id")
    private Airport arrivalAirport;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date departureDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date arrivalDate;
    private double price;
}
