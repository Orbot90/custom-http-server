package ru.orbot90.http.server.request;

import ru.orbot90.http.server.request.url.ParsedUrl;

/**
 * Created by orbot on 24.12.2017.
 */
public class RequestLine {
    private RequestMethod requestMethod;
    private ParsedUrl parsedUrl;
    private String httpVersion;

    public RequestMethod getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(RequestMethod requestMethod) {
        this.requestMethod = requestMethod;
    }

    public ParsedUrl getParsedUrl() {
        return parsedUrl;
    }

    public void setParsedUrl(ParsedUrl parsedUrl) {
        this.parsedUrl = parsedUrl;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }
}
