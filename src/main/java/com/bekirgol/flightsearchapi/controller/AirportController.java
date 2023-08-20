package com.bekirgol.flightsearchapi.controller;

import com.bekirgol.flightsearchapi.data.dto.AirportDto;
import com.bekirgol.flightsearchapi.data.request.CreateAirportRequest;
import com.bekirgol.flightsearchapi.data.response.BaseResponse;
import com.bekirgol.flightsearchapi.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airports")
@RequiredArgsConstructor
public class AirportController {
    private final AirportService airportService;

    @PostMapping("/create")
    public ResponseEntity<BaseResponse<?>> createAirport(@RequestBody CreateAirportRequest createAirportRequest) {
        try {
            AirportDto airportDto = airportService.createAirport(createAirportRequest);
            return ResponseEntity.ok(BaseResponse.builder().success(true).response(airportDto).build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(BaseResponse.builder().success(false).errorMessage(e.getMessage()).build());
        }
    }

    @GetMapping("/list-all")
    public ResponseEntity<BaseResponse<?>> listAll() {
        try {
            List<AirportDto> airportDtoList = airportService.listAllAirport();
            return ResponseEntity.ok(BaseResponse.builder().success(true).response(airportDtoList).build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(BaseResponse.builder().success(false).errorMessage(e.getMessage()).build());
        }
    }

    @GetMapping("/list-by-id/{airportId}")
    public ResponseEntity<BaseResponse<?>> listById(@PathVariable long airportId) {
        try {
            AirportDto airportDto = airportService.listById(airportId);
            return ResponseEntity.ok(BaseResponse.builder().success(true).response(airportDto).build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(BaseResponse.builder().success(false).errorMessage(e.getMessage()).build());
        }
    }

    @PatchMapping("/update/{airportId}")
    public ResponseEntity<BaseResponse<?>> updateAirport(@PathVariable long airportId, @RequestBody CreateAirportRequest createAirportRequest) {
        try {
            AirportDto airportDto = airportService.updateAirport(airportId, createAirportRequest);
            return ResponseEntity.ok(BaseResponse.builder().success(true).response(airportDto).build());

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(BaseResponse.builder().success(false).errorMessage(e.getMessage()).build());
        }
    }

    @DeleteMapping("/delete/{airportId}")
    public ResponseEntity<BaseResponse<?>> deleteAirport(@PathVariable long airportId) {
        try {
            airportService.deleteAirport(airportId);
            return ResponseEntity.ok(BaseResponse.builder().success(true).response("Airport has been deleted").build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(BaseResponse.builder().success(false).errorMessage(e.getMessage()).build());
        }
    }
}
