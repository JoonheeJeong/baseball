package controller;

import exception.IllegalParameterException;

import java.util.HashMap;
import java.util.StringTokenizer;

public interface BaseballController {
    void insert(String queryString);

    void select(String queryString);

    boolean validateParameter(HashMap<String, String> map);

    default HashMap<String, String> parameterParser(String queryString) throws IllegalParameterException {
        HashMap<String, String> map = new HashMap<>();
        StringTokenizer getParameterSetToken = new StringTokenizer(queryString, "&");
        while (getParameterSetToken.hasMoreTokens()) {
            StringTokenizer getParameterToken = new StringTokenizer(getParameterSetToken.nextToken(), " =");
            String key = getParameterToken.nextToken();
            String value = getParameterToken.nextToken();
            map.put(key, value);
        }
        return map;
    }
}
