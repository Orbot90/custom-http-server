package ru.orbot90.http.server.request;

import org.apache.commons.lang3.StringUtils;
import ru.orbot90.http.server.request.exception.MethodNotImplementedException;
import ru.orbot90.http.server.request.header.RequestHeaders;
import ru.orbot90.http.server.request.url.ParsedUrl;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by orbot on 24.12.2017.
 */
public class RequestParserImpl implements RequestParser {

    private final String requestString;
    private HttpRequest parsedRequest;

    public RequestParserImpl(String request) {
        this.requestString = request;
        parsedRequest = new HttpRequest();
    }

    @Override
    public HttpRequest parse() {
        String[] requestLines = this.requestString.split("\n");
        this.parseRequestLine(requestLines[0]);
        this.parseHeaders(requestLines);
        return parsedRequest;
    }

    private void parseHeaders(String[] requestLines) {
        RequestHeaders requestHeaders = new RequestHeaders();
        Map<String, String> requestHeadersMap = new LinkedHashMap<>();
        for (int i = 1; i < requestLines.length; i++) {
            if (StringUtils.isEmpty(requestLines[i])) {
                break;
            }

            String headerRow = requestLines[i];
            int indexOfColon = headerRow.indexOf(':');
            requestHeadersMap.put(headerRow.substring(0, indexOfColon), headerRow.substring(indexOfColon + 1).trim());
        }
        requestHeaders.addAllHeaders(requestHeadersMap);
        this.parsedRequest.setRequestHeaders(requestHeaders);
    }

    private void parseRequestLine(String requestLine) {
        RequestLine requestLineObject = new RequestLine();
        String[] requestLineTokens = requestLine.split(" ");
        RequestMethod method = RequestMethod.getByName(requestLineTokens[0]);
        if (method == RequestMethod.UNDEFINED) {
            throw new MethodNotImplementedException("Method " + requestLineTokens[0] + " is not implemented");
        }
        requestLineObject.setRequestMethod(method);

        ParsedUrl parsedUrl = this.parseUri(requestLineTokens[1]);

        requestLineObject.setParsedUrl(parsedUrl);
        requestLineObject.setHttpVersion(requestLineTokens[2]);

        parsedRequest.setRequestLine(requestLineObject);
    }

    private ParsedUrl parseUri(String urlToken) {
        ParsedUrl url = new ParsedUrl();
        boolean absoluteUrl = false;
        if (urlToken.equals("*")) {
            url.setPathElements(Collections.singletonList("*"));
            return url;
        } else if (urlToken.indexOf("http://") == 0 ||
                urlToken.indexOf("https://") == 0) {
            absoluteUrl = true;
            url.setAbsoluteUrl(true);
        }

        int indexOfWildcard = urlToken.indexOf('?');

        String pathPart;
        if (indexOfWildcard > 0) {
            pathPart = urlToken.substring(0, indexOfWildcard);
            String parametersPart = urlToken.substring(indexOfWildcard + 1);
            this.parseParameters(url, parametersPart);
        } else {
            pathPart = urlToken;
        }

        this.parseUrlForPathElements(pathPart, absoluteUrl, url);

        return url;
    }

    private void parseParameters(ParsedUrl url, String parametersPart) {
        String[] parametersTokens = parametersPart.split("&");
        Map<String, List<String>> parameters = Arrays.stream(parametersTokens)
                .map(parameterToken -> parameterToken.split("="))
                .collect(Collectors.toMap(parameterToken -> parameterToken[0],
                        parametersToken -> new ArrayList<>(Collections.singleton(parametersToken[1])),
                        (list1, list2) -> {
                            list2.addAll(list1);
                            return list2;
                        }));

        url.setUrlParameters(parameters);
    }

    private void parseUrlForPathElements(String url, boolean absoluteUrl, ParsedUrl parsedUrl) {
        String pathPartWithoutHost;
        if (absoluteUrl) {
            int startIndexOfHost = url.indexOf(':') + 1;
            String trimmedUrl = url.substring(startIndexOfHost).replaceFirst("//", "");
            int indexofHierarchy = trimmedUrl.indexOf('/');
            pathPartWithoutHost = trimmedUrl.substring(indexofHierarchy);
            parsedUrl.setSchemeWithHostPart(url.substring(0, startIndexOfHost + indexofHierarchy + 2));
        } else {
            pathPartWithoutHost = url;
        }
        parsedUrl.setPathElements(Arrays.stream(pathPartWithoutHost.split("/"))
                .filter(pathElement -> !StringUtils.isEmpty(pathElement))
                .collect(Collectors.toList()));
    }
}
