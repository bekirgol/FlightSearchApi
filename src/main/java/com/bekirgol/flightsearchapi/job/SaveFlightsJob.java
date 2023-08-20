package com.bekirgol.flightsearchapi.job;

import com.bekirgol.flightsearchapi.data.request.CreateFlightRequest;
import com.bekirgol.flightsearchapi.data.response.FlightsResponse;
import com.bekirgol.flightsearchapi.service.FlightService;
import com.gol.bekir.authendicationserver.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SaveFlightsJob {
    private final FlightService flightService;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm");
    
    @Scheduled(cron = "0 0 0 * * ?")
    public void saveFlights() {
        System.out.println("Start Job");
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String startDate = today.format(dateFormatter);
        String endDate = tomorrow.format(dateFormatter);

        try {
            List<FlightsResponse> flightsResponses = flightService.getFlights(startDate, endDate);
            for (FlightsResponse flight : flightsResponses) {
                flightService.createFlight(createFlightRequest(flight));
            }
        } catch (BadRequestException e) {
            throw new RuntimeException(e);
        }
    }

    private CreateFlightRequest createFlightRequest(FlightsResponse flight) {
        CreateFlightRequest createFlightRequest = new CreateFlightRequest();
        createFlightRequest.setDepartureAirportId(flight.getDepartureAirport());
        createFlightRequest.setArrivalAirportId(flight.getArrivalAirport());
        createFlightRequest.setDepartureDate(DATE_FORMAT.format(flight.getDepartureDate()));
        createFlightRequest.setArrivalDate(DATE_FORMAT.format(flight.getArrivalDate()));
        createFlightRequest.setPrice(flight.getPrice());

        return createFlightRequest;
    }
}
