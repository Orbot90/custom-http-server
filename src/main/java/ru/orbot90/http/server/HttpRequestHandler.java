package ru.orbot90.http.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.orbot90.http.server.request.HttpRequest;
import ru.orbot90.http.server.request.RequestHandler;
import ru.orbot90.http.server.request.RequestParserImpl;

public class HttpRequestHandler implements RequestHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpRequestHandler.class);

    @Override
    public String handleRequest(String request) {
        LOGGER.info("Received request: \n{}", request);

        HttpRequest httpRequest = new RequestParserImpl(request).parse();


        // TODO: return response
        return null;
    }
}
