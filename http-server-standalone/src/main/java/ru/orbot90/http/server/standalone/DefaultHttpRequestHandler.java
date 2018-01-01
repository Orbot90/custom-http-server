package ru.orbot90.http.server.standalone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.orbot90.http.server.request.HttpRequest;
import ru.orbot90.http.server.request.RequestHandler;
import ru.orbot90.http.server.request.RequestMethod;
import ru.orbot90.http.server.request.RequestParserImpl;
import ru.orbot90.http.server.request.exception.HttpException;
import ru.orbot90.http.server.request.exception.MethodNotAllowedException;
import ru.orbot90.http.server.response.HttpResponse;
import ru.orbot90.http.server.response.ResponseStatusCode;

public class DefaultHttpRequestHandler implements RequestHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultHttpRequestHandler.class);

    @Override
    public String handleRequest(String request) {
        LOGGER.info("Received request: \n{}", request);

        HttpResponse response = new HttpResponse();
        try {
            HttpRequest httpRequest = new RequestParserImpl(request).parse();

            this.checkRequestMethod(httpRequest);
            response.setResponseStatusCode(ResponseStatusCode.OK);
        } catch (HttpException e) {
            LOGGER.error("Error handling request", e);
            response.setResponseStatusCode(e.getResponseStatusCode());
        } catch (Exception e) {
            LOGGER.error("Error handling request", e);
            response.setResponseStatusCode(ResponseStatusCode.INTERNAL_SERVER_ERROR);
        }


        return response.toString();
    }

    private void checkRequestMethod(HttpRequest httpRequest) {
        if (httpRequest.getRequestMethod() != RequestMethod.GET) {
            throw new MethodNotAllowedException("Only GET method is allowed in default handler");
        }
    }
}
