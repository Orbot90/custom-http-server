package ru.orbot90.http.server.request.header;

import java.util.LinkedHashMap;
import java.util.Map;

public class RequestHeaders {
    private final Map<String, RequestHeader> requestHeaders = new LinkedHashMap<>();

    public void addHeader(String key, String value) {
        RequestHeader requestHeader = new RequestHeader(key, value.trim());
        requestHeaders.put(key.toLowerCase(), requestHeader);
    }

    public String getHeaderValue(String headerKey) {
        return this.requestHeaders.get(headerKey.toLowerCase()).getValue();
    }

    public void addAllHeaders(Map<String, String> headers) {
        for (Map.Entry<String, String> header : headers.entrySet()) {
            this.addHeader(header.getKey(), header.getValue());
        }
    }

    @Override
    public String toString() {
        StringBuilder headers = new StringBuilder();
        for (Map.Entry<String, RequestHeader> header : requestHeaders.entrySet()) {
            headers.append(header.getValue()).append('\n');
        }
        return headers.toString();
    }
}
