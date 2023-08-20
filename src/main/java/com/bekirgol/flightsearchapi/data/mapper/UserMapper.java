package com.bekirgol.flightsearchapi.data.mapper;

import com.bekirgol.flightsearchapi.data.dto.UserDto;
import com.bekirgol.flightsearchapi.data.entity.User;
import com.bekirgol.flightsearchapi.data.request.CreateUserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    User createUser(CreateUserRequest createUserRequest);

    UserDto convertUserDto(User user);
}
