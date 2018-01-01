package ru.orbot90.http.server.response;

public enum ResponseStatusCode {

    OK(200, "OK"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    NOT_IMPLEMENTED(501, "Not Implemented");

    private String statusMessage;
    private int statusNumber;

    ResponseStatusCode(int statusNumber, String statusMessage) {
        this.statusNumber = statusNumber;
        this.statusMessage = statusMessage;
    }
}
