package ru.orbot90.http.server.request;

/**
 * Object representation for HTTP request
 */
public class HttpRequest {
    private RequestLine requestLine;

    public RequestLine getRequestLine() {
        return requestLine;
    }

    public void setRequestLine(RequestLine requestLine) {
        this.requestLine = requestLine;
    }

    @Override
    public String toString() {
        return requestLine.getRequestMethod().getMethodName() + " " +
                requestLine.getParsedUrl() + " " +
                requestLine.getHttpVersion();
    }
}
