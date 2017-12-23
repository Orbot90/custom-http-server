package ru.orbot90.http.server.request;

import ru.orbot90.http.server.request.url.ParsedUrl;

/**
 * Object representation for HTTP request
 */
public class HttpRequest {
    private ParsedUrl parsedUrl;

    public ParsedUrl getParsedUrl() {
        return parsedUrl;
    }

    public void setParsedUrl(ParsedUrl parsedUrl) {
        this.parsedUrl = parsedUrl;
    }
}
