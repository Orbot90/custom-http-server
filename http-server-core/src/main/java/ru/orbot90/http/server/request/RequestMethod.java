package ru.orbot90.http.server.request;

import java.util.Arrays;

/**
 * Created by orbot on 24.12.2017.
 */
public enum RequestMethod {

    OPTIONS("OPTIONS"),
    GET("GET"),
    HEAD("HEAD"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE"),
    TRACE("TRACE"),
    CONNECT("CONNECT"),
    UNDEFINED("undefined");

    private String method;

    RequestMethod(String method) {
        this.method = method;
    }

    public String getMethodName() {
        return this.method;
    }

    public static RequestMethod getByName(String methodName) {
        return Arrays.stream(RequestMethod.values())
                .filter(method -> method.method.equals(methodName))
                .findFirst()
                .orElse(UNDEFINED);
    }
}
