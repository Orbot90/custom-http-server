package ru.orbot90.http.server.request.url;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by orbot on 23.12.2017.
 */
public class ParsedUrl {
    private List<String> pathElements;
    private Map<String, List<String>> urlParameters = new HashMap<>();
    private boolean absoluteUrl;
    private String schemeWithHostPart = "";

    public List<String> getPathElements() {
        return pathElements;
    }

    public void setPathElements(List<String> pathElements) {
        this.pathElements = pathElements;
    }

    public Map<String, List<String>> getUrlParameters() {
        return urlParameters;
    }

    public void setUrlParameters(Map<String, List<String>> urlParameters) {
        this.urlParameters = urlParameters;
    }

    public boolean isAbsoluteUrl() {
        return absoluteUrl;
    }

    public void setAbsoluteUrl(boolean absoluteUrl) {
        this.absoluteUrl = absoluteUrl;
    }

    public String getSchemeWithHostPart() {
        return schemeWithHostPart;
    }

    public void setSchemeWithHostPart(String schemeWithHostPart) {
        this.schemeWithHostPart = schemeWithHostPart;
    }

    @Override
    public String toString() {
        StringBuilder urlString = new StringBuilder();
        urlString.append( schemeWithHostPart)
                .append(String.join("/", pathElements));
        if (!urlParameters.isEmpty()) {
            urlString.append('?');
            StringBuilder parametersLine = new StringBuilder();
            for (Map.Entry<String, List<String>> entry : urlParameters.entrySet()) {
                entry.getValue().forEach(parameterValue -> parametersLine.append("&")
                        .append(entry.getKey())
                        .append('=')
                        .append(parameterValue));
            }
            urlString.append(parametersLine.toString()
                    .replaceFirst("&", ""));
        }
        return urlString.toString();
    }
}
