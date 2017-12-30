package ru.orbot90.http.server.request;

/**
 * Parser for received HTTP request
 */
public interface RequestParser {

    /**
     * Parse received request
     *
     * @return Object representation of received request
     */
    HttpRequest parse();

}
