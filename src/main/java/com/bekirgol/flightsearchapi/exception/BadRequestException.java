package com.gol.bekir.authendicationserver.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BadRequestException extends Exception {
    private String message;
    private int status;
}
