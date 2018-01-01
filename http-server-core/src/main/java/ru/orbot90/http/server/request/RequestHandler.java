package ru.orbot90.http.server.request;

/**
 * Handler for received requests
 */
public interface RequestHandler {

    /**
     * Handler received request
     *
     * @param request String representation of HTTP request
     * @return String representation of HTTP response
     */
    String handleRequest(String request);
}
