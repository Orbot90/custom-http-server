package ru.orbot90.http.server.request.url;

import java.util.List;
import java.util.Map;

/**
 * Created by orbot on 23.12.2017.
 */
public class ParsedUrl {
    private List<String> pathElements;
    private Map<String, String> urlParameters;

    public List<String> getPathElements() {
        return pathElements;
    }

    public void setPathElements(List<String> pathElements) {
        this.pathElements = pathElements;
    }

    public Map<String, String> getUrlParameters() {
        return urlParameters;
    }

    public void setUrlParameters(Map<String, String> urlParameters) {
        this.urlParameters = urlParameters;
    }
}
