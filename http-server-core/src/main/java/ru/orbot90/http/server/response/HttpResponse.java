package ru.orbot90.http.server.response;

public class HttpResponse {
    private ResponseStatusCode responseStatusCode;

    public ResponseStatusCode getResponseStatusCode() {
        return responseStatusCode;
    }

    public void setResponseStatusCode(ResponseStatusCode responseStatusCode) {
        this.responseStatusCode = responseStatusCode;
    }
}
