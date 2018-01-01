package ru.orbot90.http.server.request.exception;

import ru.orbot90.http.server.response.ResponseStatusCode;

public class MethodNotImplementedException extends HttpException {
    public MethodNotImplementedException(String message) {
        super(message, ResponseStatusCode.NOT_IMPLEMENTED);
    }
}
