package ru.orbot90.http.server.request;

import ru.orbot90.http.server.request.header.RequestHeaders;

/**
 * Object representation for HTTP request
 */
public class HttpRequest {
    private RequestLine requestLine;
    private RequestHeaders requestHeaders;

    RequestLine getRequestLine() {
        return requestLine;
    }

    void setRequestLine(RequestLine requestLine) {
        this.requestLine = requestLine;
    }

    void setRequestHeaders(RequestHeaders requestHeaders) {
        this.requestHeaders = requestHeaders;
    }

    public RequestMethod getRequestMethod() {
        return this.requestLine.getRequestMethod();
    }

    public String getHeader(String headerKey) {
        return this.requestHeaders.getHeaderValue(headerKey);
    }

    @Override
    public String toString() {
        return requestLine + "\n" +
                requestHeaders + "\n";
    }
}
