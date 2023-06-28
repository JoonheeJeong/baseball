package controller;

import exception.IllegalParameterException;
import service.BaseBallService;
import service.StadiumService;
import util.annotation.Controller;
import util.annotation.RequestMapping;

import java.util.HashMap;

@Controller
public class StadiumController implements BaseballController {
    private BaseBallService stadiumService = new StadiumService();

    @Override
    @RequestMapping(uri = "야구장등록")
    public void insert(String queryString) {
        HashMap<String, String> map = parameterParser(queryString);
        validateParameter(map);
        stadiumService.register(map);
    }

    @Override
    @RequestMapping(uri = "야구장목록")
    public void select(String queryString) {
        HashMap<String, String> map = parameterParser(queryString);
        stadiumService.show(map);
    }

    @Override
    public boolean validateParameter(HashMap<String, String> map) {
        if (map.containsKey("name")) {
            return true;
        }
        throw new IllegalParameterException("알맞은 파라미터명이 아닙니다.");
    }


}