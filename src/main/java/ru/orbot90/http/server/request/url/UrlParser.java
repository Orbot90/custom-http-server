package ru.orbot90.http.server.request.url;

/**
 * Created by orbot on 23.12.2017.
 */
public interface UrlParser {

    ParsedUrl parse(String urlString);
}
