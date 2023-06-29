package controller;

import exception.IllegalParameterException;
import service.BaseBallService;
import service.StadiumService;
import util.annotation.Controller;
import util.annotation.RequestMapping;
import util.annotation.RequestMethod;

import java.util.HashMap;

@Controller
public class StadiumController implements BaseballController {
    private final BaseBallService stadiumService;

    public StadiumController() {
        this.stadiumService = StadiumService.getInstance();
    }

    @Override
    @RequestMapping(uri = "야구장등록", method = RequestMethod.POST)
    public void insert(String queryString) {
        HashMap<String, String> map = parameterParser(queryString);
        validateParameter(map);
        stadiumService.register(map);
    }

    @Override
    @RequestMapping(uri = "야구장목록", method = RequestMethod.GET)
    public void select() {
        stadiumService.show();
    }

    @Override
    public boolean validateParameter(HashMap<String, String> map) {
        if (map.containsKey("name"))
            return true;

        throw new IllegalParameterException("알맞은 파라미터명이 아닙니다.");
    }


}