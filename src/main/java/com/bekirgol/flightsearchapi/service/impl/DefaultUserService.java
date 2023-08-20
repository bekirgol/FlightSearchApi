package com.bekirgol.flightsearchapi.service.impl;

import com.bekirgol.flightsearchapi.data.dto.UserDto;
import com.bekirgol.flightsearchapi.data.entity.User;
import com.bekirgol.flightsearchapi.data.request.CreateUserRequest;
import com.bekirgol.flightsearchapi.data.request.LoginRequest;
import com.bekirgol.flightsearchapi.data.response.AuthenticationResponse;
import com.bekirgol.flightsearchapi.exception.UserNotFoundException;
import com.bekirgol.flightsearchapi.repository.UserRepository;
import com.bekirgol.flightsearchapi.service.UserService;
import com.gol.bekir.authendicationserver.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import static com.bekirgol.flightsearchapi.data.mapper.UserMapper.USER_MAPPER;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto register(CreateUserRequest createUserRequest) {
        User user = USER_MAPPER.createUser(createUserRequest);
        user.setPassword(bCryptPasswordEncoder.encode(createUserRequest.getPassword()));
        return USER_MAPPER.convertUserDto(userRepository.save(user));
    }

    @Override
    public UserDto login(LoginRequest loginRequest) throws BadRequestException, UserNotFoundException {
        User user = userRepository.findByEmail(loginRequest.getEmail());
        if (!bCryptPasswordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new BadRequestException("Wrong password", 401);
        }
        return USER_MAPPER.convertUserDto(user);
    }
}
