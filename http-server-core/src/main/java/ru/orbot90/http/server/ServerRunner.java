package ru.orbot90.http.server;

import ru.orbot90.http.server.request.RequestHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Runner loop of the server
 */
public class ServerRunner {

    private Integer serverPort;
    private ExecutorService serverExecutor;
    private RequestHandler requestHandler;

    public ServerRunner() {
        this.serverPort = serverPort;
    }

    public ServerRunner serverPort(int serverPort) {
        this.serverPort = serverPort;
        return this;
    }

    public ServerRunner threadPoolSize(int threadPoolSize) {
        this.serverExecutor = Executors.newFixedThreadPool(threadPoolSize);
        return this;
    }

    public ServerRunner requestHandler(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
        return this;
    }

    public void run() throws IOException {
        this.checkPort();
        this.checkExecutor();
        this.checkRequestHandler();
        try (ServerSocket serverSocket = new ServerSocket(serverPort)) {
            while (true) {
                Socket client = serverSocket.accept();
                ConnectionThreadedHandler handler = new ConnectionThreadedHandler(client, requestHandler);
                serverExecutor.submit(handler);
            }
        }
    }

    private void checkPort() {
        if (this.serverPort == null) {
            throw new IllegalStateException("Server port is not set");
        }
    }

    private void checkExecutor() {
        if (this.serverExecutor == null) {
            throw new IllegalStateException("Thread pool is not initialized");
        }
    }

    private void checkRequestHandler() {
        if (this.requestHandler == null) {
            throw new IllegalStateException("Request handler is not set");
        }
    }
}
