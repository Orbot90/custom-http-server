package ru.orbot90.http.server;

import ru.orbot90.http.server.request.RequestHandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Temporary request handler for testing purposes
 */
public class TemporaryTestRequestHandler implements RequestHandler {
    public String handleRequest(String request) {
        try {
            System.out.println(request);
            System.out.println();
            System.out.println();

            // As a lazy bitch I've put whole response to html file
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(
                    Application.class.getClassLoader().getResourceAsStream("test.html")));
            String pageLine = fileReader.readLine();
            StringBuilder pageBuilder = new StringBuilder();
            while (pageLine != null) {
                pageBuilder.append(pageLine).append("\n");
                pageLine = fileReader.readLine();
            }
            return pageBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
