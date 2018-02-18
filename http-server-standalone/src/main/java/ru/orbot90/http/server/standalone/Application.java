package ru.orbot90.http.server.standalone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.orbot90.http.server.ServerRunner;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Server main
 */
public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        LOGGER.info("Starting application");

        try {
            Properties properties = new Properties();
            properties.load(new InputStreamReader(Application.class.getClassLoader().getResourceAsStream("server-config.properties")));
            int threadPoolSize = Integer.valueOf(properties.getProperty("server.threadpool.size"));
            int serverPort = Integer.valueOf(properties.getProperty("server.port"));
            new ServerRunner().threadPoolSize(threadPoolSize)
                    .serverPort(serverPort)
                    .requestHandler(new TemporaryTestRequestHandler())
                    .run();
        } catch (Throwable e) {
            LOGGER.error("Error running server. Application is stopped", e);
            System.exit(1);
        }
    }
}
