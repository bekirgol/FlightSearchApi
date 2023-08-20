package com.bekirgol.flightsearchapi.controller;

import com.bekirgol.flightsearchapi.data.dto.FlightDto;
import com.bekirgol.flightsearchapi.data.entity.Flight;
import com.bekirgol.flightsearchapi.data.request.CreateFlightRequest;
import com.bekirgol.flightsearchapi.data.response.BaseResponse;
import com.bekirgol.flightsearchapi.repository.AirportRepository;
import com.bekirgol.flightsearchapi.repository.FlightRepository;
import com.bekirgol.flightsearchapi.service.AirportService;
import com.bekirgol.flightsearchapi.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/flights")
@RequiredArgsConstructor
public class FlightController {

    private final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh-mm");

    private final FlightService flightService;
    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;


    @PostMapping("/create")
    public ResponseEntity<BaseResponse<?>> createFlight(@RequestBody CreateFlightRequest createFlightRequest) {
        try {
            flightService.createFlight(createFlightRequest);
            return ResponseEntity.ok(BaseResponse.builder().success(true).response("Flight has been created successfully").build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(BaseResponse.builder().success(false).errorMessage(e.getMessage()).build());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<BaseResponse<?>> search(@RequestParam Long departureAirport,
                                                  @RequestParam Long arrivalAirport,
                                                  @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date departureDate,
                                                  @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date arrivalDate) {
        try {
            List<FlightDto> flightDtoList = flightService.search(departureAirport, arrivalAirport, departureDate, arrivalDate);
            return ResponseEntity.ok(BaseResponse.builder().success(true).response(flightDtoList).build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(BaseResponse.builder().success(false).errorMessage(e.getMessage()).build());
        }
    }

    @GetMapping("/list-all")
    public ResponseEntity<BaseResponse<?>> listAll() {
        try {
            List<FlightDto> flightDtoList = flightService.listAllFlight();
            return ResponseEntity.ok(BaseResponse.builder().success(true).response(flightDtoList).build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(BaseResponse.builder().success(false).errorMessage(e.getMessage()).build());
        }
    }

    @GetMapping("/list-by-id/{flightId}")
    public ResponseEntity<BaseResponse<?>> listAll(@PathVariable Long flightId) {
        try {
            FlightDto flight = flightService.listById(flightId);
            return ResponseEntity.ok(BaseResponse.builder().success(true).response(flight).build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(BaseResponse.builder().success(false).errorMessage(e.getMessage()).build());
        }
    }

    @PatchMapping("/update/{flightId}")
    public ResponseEntity<BaseResponse<?>> update(@PathVariable Long flightId, @RequestBody CreateFlightRequest createFlightRequest) {
        try {
            flightService.updateFlight(flightId, createFlightRequest);
            return ResponseEntity.ok(BaseResponse.builder().success(true).response("Flight has been updated successfully").build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(BaseResponse.builder().success(false).errorMessage(e.getMessage()).build());
        }
    }

    @DeleteMapping("/delete/{flightId}")
    public ResponseEntity<BaseResponse<?>> delete(@PathVariable Long flightId) {
        try {
            flightService.deleteFlight(flightId);
            return ResponseEntity.ok(BaseResponse.builder().success(true).response("Flight has been deleted successfully").build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(BaseResponse.builder().success(false).errorMessage(e.getMessage()).build());
        }
    }
}
