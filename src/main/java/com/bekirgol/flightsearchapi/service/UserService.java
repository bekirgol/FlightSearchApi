package com.bekirgol.flightsearchapi.service;

import com.bekirgol.flightsearchapi.data.dto.UserDto;
import com.bekirgol.flightsearchapi.data.request.CreateUserRequest;
import com.bekirgol.flightsearchapi.data.request.LoginRequest;
import com.bekirgol.flightsearchapi.data.response.AuthenticationResponse;
import com.bekirgol.flightsearchapi.exception.UserNotFoundException;

public interface UserService {
    UserDto register(CreateUserRequest createUserRequest);

    UserDto login(LoginRequest loginRequest) throws com.gol.bekir.authendicationserver.exception.BadRequestException, UserNotFoundException;
}
