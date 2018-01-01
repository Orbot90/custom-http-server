package ru.orbot90.http.server.standalone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.orbot90.http.server.request.RequestHandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Temporary request handler for testing purposes
 */
public class TemporaryTestRequestHandler implements RequestHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(TemporaryTestRequestHandler.class);

    public String handleRequest(String request) {
        try {
            LOGGER.info("Received request: \n{}", request);

            // As a lazy bitch I've put whole response to html file
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(
                    TemporaryTestRequestHandler.class.getClassLoader().getResourceAsStream("test.html")));
            String pageLine = fileReader.readLine();
            StringBuilder pageBuilder = new StringBuilder();
            while (pageLine != null) {
                pageBuilder.append(pageLine).append("\n");
                pageLine = fileReader.readLine();
            }
            return pageBuilder.toString();
        } catch (Exception e) {
            LOGGER.error("Error", e);
            return "";
        }
    }
}
