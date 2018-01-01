package ru.orbot90.http.server.request.exception;

import ru.orbot90.http.server.response.ResponseStatusCode;

public class HttpException extends RuntimeException {
    private ResponseStatusCode responseStatusCode;

    public HttpException(String message, ResponseStatusCode responseStatusCode) {
        super(message);
        this.responseStatusCode = responseStatusCode;
    }

    public ResponseStatusCode getResponseStatusCode() {
        return this.responseStatusCode;
    }
}
