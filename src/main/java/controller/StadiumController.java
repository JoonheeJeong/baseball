package controller;

import service.BaseBallService;
import service.StadiumService;
import util.annotation.Controller;
import util.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

@Controller
public class StadiumController {
    private BaseBallService stadiumService = new StadiumService();

//    public StadiumController(BaseBallService stadiumService) {
//        this.stadiumService = stadiumService;
//    }

    @RequestMapping(uri = "야구장등록")
    public void insertStadium(String queryString) {
        HashMap<String, String> map = requestParser(queryString);
        stadiumService.register(map);
    }

    @RequestMapping(uri = "야구장목록")
    public void selectStadium(String queryString) {
        stadiumService.show();
    }

    private HashMap<String, String> requestParser(String queryString) {
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