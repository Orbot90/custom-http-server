package ru.orbot90.http.server;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.orbot90.http.server.request.RequestHandler;

import java.io.*;
import java.net.Socket;

/**
 * Multithreaded connection handler of server
 */
public class ConnectionThreadedHandler implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionThreadedHandler.class);

    private final Socket client;
    private final RequestHandler requestHandler;

    public ConnectionThreadedHandler(Socket client, RequestHandler requestHandler) {
        this.client = client;
        this.requestHandler = requestHandler;
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
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                LOGGER.debug("Client socket closing failed", e);
            }
        }
    }
}
