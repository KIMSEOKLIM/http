package kr.co.mz.tutorial.http.parser;

import java.util.HashMap;
import java.util.Map;

public class QueryParam {

    private final String request;
    private final Map<String, String> params;

    public QueryParam(String request) {
        this.params = new HashMap<>();
        this.request = request;
    }

    public String getRequest() {
        return request;
    }

    public Map<String, String> getQueryParams(String request) {
        int questionIndex = request.indexOf('?');
        if (questionIndex != -1) {
            String queryString = request.substring(questionIndex + 1);
            String[] pairs = queryString.split("&");
            for (String pair : pairs) {
                String[] keyValue = pair.split("=");
                if (keyValue.length == 2) {
                    String key = keyValue[0];
                    String value = keyValue[1].split(" ")[0];
                    System.out.println(key + " " + value);
                    params.put(key, value);
                }
            }
        }
        return params;
    }
}
