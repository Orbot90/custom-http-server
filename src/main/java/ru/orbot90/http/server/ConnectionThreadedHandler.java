package ru.orbot90.http.server;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.orbot90.http.server.request.RequestHandler;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Multithreaded connection handler of server
 */
public class ConnectionThreadedHandler implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionThreadedHandler.class);
    private final Socket client;

    private final RequestHandler requestHandler = new TemporaryTestRequestHandler();

    public ConnectionThreadedHandler(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try (DataInputStream dataInputStream = new DataInputStream(client.getInputStream());
             DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream())) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(dataInputStream));
            String line = reader.readLine();
            StringBuilder request = new StringBuilder();
            while (!StringUtils.isEmpty(line)) {
                request.append(line)
                        .append("\n");
                line = reader.readLine();
            }

            String response = requestHandler.handleRequest(request.toString());

            dataOutputStream.writeBytes(response);
        } catch (Exception e) {
            LOGGER.error("Connection error", e);
        }
    }
}