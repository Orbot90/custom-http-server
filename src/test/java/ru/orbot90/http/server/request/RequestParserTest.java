package ru.orbot90.http.server.request;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class RequestParserTest {

    private static final String TEST_REQUEST_NO_HOST = "GET /html/rfc3986 HTTP/1.1\n" +
            "Host: tools.ietf.org\n" +
            "Connection: keep-alive\n" +
            "Cache-Control: max-age=0\n" +
            "Upgrade-Insecure-Requests: 1\n" +
            "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36\n" +
            "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8\n" +
            "Accept-Encoding: gzip, deflate, br\n" +
            "Accept-Language: ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7\n" +
            "If-None-Match: \"2240d77-2d617-56084ccf34d80;5618d5e525b46\"\n" +
            "If-Modified-Since: Sun, 17 Dec 2017 08:12:54 GMT\n";
    private static final String TEST_REQUEST_WITH_HOST = "GET https://tools.ietf.org/html/rfc3986 HTTP/1.1\n" +
            "Host: tools.ietf.org\n" +
            "Connection: keep-alive\n" +
            "Cache-Control: max-age=0\n" +
            "Upgrade-Insecure-Requests: 1\n" +
            "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36\n" +
            "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8\n" +
            "Accept-Encoding: gzip, deflate, br\n" +
            "Accept-Language: ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7\n" +
            "If-None-Match: \"2240d77-2d617-56084ccf34d80;5618d5e525b46\"\n" +
            "If-Modified-Since: Sun, 17 Dec 2017 08:12:54 GMT\n";
    private static final String TEST_REQUEST_WITH_PARAMETERS = "GET /html/rfc3986?test=1&dummy=3 HTTP/1.1\n" +
            "Host: tools.ietf.org\n" +
            "Connection: keep-alive\n" +
            "Cache-Control: max-age=0\n" +
            "Upgrade-Insecure-Requests: 1\n" +
            "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36\n" +
            "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8\n" +
            "Accept-Encoding: gzip, deflate, br\n" +
            "Accept-Language: ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7\n" +
            "If-None-Match: \"2240d77-2d617-56084ccf34d80;5618d5e525b46\"\n" +
            "If-Modified-Since: Sun, 17 Dec 2017 08:12:54 GMT\n";

    @Test
    public void testParseRequestLineWithoutHost() {
        RequestParser requestParser = new RequestParserImpl(TEST_REQUEST_NO_HOST);
        HttpRequest parsedRequest = requestParser.parse();
        assertFalse("Url is absolute", parsedRequest.getRequestLine().getParsedUrl().isAbsoluteUrl());
        List<String> expectedPathElements = Arrays.asList("html", "rfc3986");
        assertEquals("Wrong path elements", expectedPathElements, parsedRequest.getRequestLine().getParsedUrl().getPathElements());
        assertEquals("Wrong http method", "GET", parsedRequest.getRequestLine().getRequestMethod().getMethodName());
        assertEquals("Wrong http version", "HTTP/1.1", parsedRequest.getRequestLine().getHttpVersion());
        assertEquals("Unexpected parameters value", Collections.emptyMap(), parsedRequest.getRequestLine().getParsedUrl().getUrlParameters());
        assertEquals("Wrong host name", "", parsedRequest.getRequestLine().getParsedUrl().getSchemeWithHostPart());
    }

    @Test
    public void testParseRequestLineWithHost() {
        RequestParser requestParser = new RequestParserImpl(TEST_REQUEST_WITH_HOST);
        HttpRequest parsedRequest = requestParser.parse();
        assertTrue("Url is not absolute", parsedRequest.getRequestLine().getParsedUrl().isAbsoluteUrl());
        List<String> expectedPathElements = Arrays.asList("html", "rfc3986");
        assertEquals("Wrong path elements", expectedPathElements, parsedRequest.getRequestLine().getParsedUrl().getPathElements());
        assertEquals("Wrong http method", "GET", parsedRequest.getRequestLine().getRequestMethod().getMethodName());
        assertEquals("Wrong http version", "HTTP/1.1", parsedRequest.getRequestLine().getHttpVersion());
        assertEquals("Unexpected parameters value", Collections.emptyMap(), parsedRequest.getRequestLine().getParsedUrl().getUrlParameters());
        assertEquals("Wrong host name", "https://tools.ietf.org", parsedRequest.getRequestLine().getParsedUrl().getSchemeWithHostPart());
    }

    @Test
    public void testParseRequestWithParameters() {
        RequestParser requestParser = new RequestParserImpl(TEST_REQUEST_WITH_PARAMETERS);
        HttpRequest parsedRequest = requestParser.parse();
        Map<String, List<String>> parameters = parsedRequest.getRequestLine().getParsedUrl().getUrlParameters();
        Map<String, List<String>> expectedParameters = new HashMap<>();
        expectedParameters.put("dummy", Collections.singletonList("3"));
        expectedParameters.put("test", Collections.singletonList("1"));
        assertEquals("Wrong parsed parameters", expectedParameters, parameters);
    }

    @Test
    public void testParseCorrectHeaders() {
        RequestParser requestParser = new RequestParserImpl(TEST_REQUEST_WITH_HOST);
        HttpRequest parsedRequest = requestParser.parse();
        String hostHeader = parsedRequest.getHeader("Host");
        assertEquals("Wrong host header value", "tools.ietf.org", hostHeader);
        String connectionHeader = parsedRequest.getHeader("Connection");
        assertEquals("Wrong connection header value", "keep-alive", connectionHeader);
        String cacheControlHeader = parsedRequest.getHeader("cache-COnTrol");
        assertEquals("Wrong cache-control header value", "max-age=0", cacheControlHeader);
    }
}