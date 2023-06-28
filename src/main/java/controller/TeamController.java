package controller;

import exception.IllegalParameterException;
import service.BaseBallService;
import service.TeamService;
import util.annotation.Controller;
import util.annotation.RequestMapping;

import java.util.HashMap;

@Controller
public class TeamController implements BaseballController{

    private BaseBallService teamService = new TeamService();

    @Override
    @RequestMapping(uri = "팀등록")
    public void insert(String queryString) {
        HashMap<String, String> map = parameterParser(queryString);
        validateParameter(map);
        teamService.register(map);
    }

    @Override
    @RequestMapping(uri = "팀목록")
    public void select(String queryString) {
        HashMap<String, String> map = parameterParser(queryString);
        teamService.show(map);
    }

    @Override
    public boolean validateParameter(HashMap<String, String> map) {
        if (map.containsKey("name") && map.containsKey("stadiumId")) {
            return true;
        }
        throw new IllegalParameterException("알맞은 파라미터명이 아닙니다.");
    }

}
