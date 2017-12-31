package ru.orbot90.http.server.request.header;

class RequestHeader {
    private final String key;
    private final String value;

    RequestHeader(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return key + ": " + value;
    }
}
