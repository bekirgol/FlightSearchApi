package com.bekirgol.flightsearchapi.controller;

import com.bekirgol.flightsearchapi.data.request.CreateUserRequest;
import com.bekirgol.flightsearchapi.data.request.LoginRequest;
import com.bekirgol.flightsearchapi.data.response.AuthenticationResponse;
import com.bekirgol.flightsearchapi.data.response.BaseResponse;
import com.bekirgol.flightsearchapi.exception.UserNotFoundException;
import com.bekirgol.flightsearchapi.service.UserService;
import com.bekirgol.flightsearchapi.util.helper.JWTUtil;
import com.gol.bekir.authendicationserver.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JWTUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @PostMapping("/register")
    public ResponseEntity<BaseResponse<?>> register(@RequestBody CreateUserRequest createUserRequest) {
        try {
            userService.register(createUserRequest);
            UserDetails userDetails = userDetailsService.loadUserByUsername(createUserRequest.getEmail());
            String token = jwtUtil.generateToken(userDetails);
            return ResponseEntity
                    .ok(BaseResponse.builder().success(true).response(AuthenticationResponse.builder().token(token).build()).build());
        } catch (DuplicateKeyException duplicateKeyException) {
            return ResponseEntity
                    .badRequest()
                    .body(BaseResponse.builder().success(false).errorMessage("User already exist").build());
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError().
                    body(BaseResponse.builder().errorMessage(e.getMessage()).success(false).build());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponse<?>> login(@RequestBody LoginRequest loginRequest) {
        try {
            userService.login(loginRequest);
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
            String token = jwtUtil.generateToken(userDetails);
            return ResponseEntity
                    .ok(BaseResponse.builder().success(true).response(AuthenticationResponse.builder().token(token).build()).build());
        } catch (UserNotFoundException userNotFoundException) {
            return ResponseEntity
                    .status(userNotFoundException.getStatus())
                    .body(BaseResponse.builder().success(false).errorMessage(userNotFoundException.getMessage()).build());
        } catch (BadRequestException badRequestException) {
            return ResponseEntity
                    .status(badRequestException.getStatus())
                    .body(BaseResponse.builder().success(false).errorMessage(badRequestException.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(BaseResponse.builder().success(false).errorMessage(e.getMessage()).build());
        }
    }
}
