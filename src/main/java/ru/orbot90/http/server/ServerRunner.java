package ru.orbot90.http.server;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Runner loop of the server
 */
public class ServerRunner {

    private int serverPort;
    private ExecutorService serverExecutor;

    public ServerRunner(int threadPoolSize, int serverPort) {
        this.serverPort = serverPort;
        serverExecutor = Executors.newFixedThreadPool(threadPoolSize);
    }

    public void run() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(serverPort)) {
            while (true) {
                Socket client = serverSocket.accept();
                ConnectionThreadedHandler handler = new ConnectionThreadedHandler(client);
                serverExecutor.submit(handler);
            }
        }
    }
}
