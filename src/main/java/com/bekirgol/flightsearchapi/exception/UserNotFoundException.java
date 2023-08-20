package com.bekirgol.flightsearchapi.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserNotFoundException extends Exception {
    private String message;
    private int status;
}
