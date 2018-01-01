package ru.orbot90.http.server.request.exception;

import ru.orbot90.http.server.response.ResponseStatusCode;

public class MethodNotAllowedException extends HttpException {


    public MethodNotAllowedException(String message) {
        super(message, ResponseStatusCode.METHOD_NOT_ALLOWED);
    }
}
