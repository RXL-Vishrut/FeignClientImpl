package com.example.userservice.userservice.clients.config;

import com.example.userservice.userservice.clients.exception.BadRequestException;
import com.example.userservice.userservice.clients.exception.UnauthorizedException;
import com.example.userservice.userservice.clients.exception.UserNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        return switch (response.status()) {
            case 400 -> new BadRequestException("The request is not valid");
            case 401 -> new UnauthorizedException("Auth token is not valid or is expired");
            case 404 -> new UserNotFoundException("User Not Found Exception");
            default -> new Exception("Exception while getting fetching user");
        };
    }
}
