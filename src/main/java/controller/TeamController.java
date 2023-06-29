package controller;

import exception.IllegalParameterException;
import service.BaseBallService;
import service.TeamService;
import util.annotation.Controller;
import util.annotation.RequestMapping;
import util.annotation.RequestMethod;

import java.util.HashMap;

@Controller
public class TeamController implements BaseballController {

    private final BaseBallService teamService;

    public TeamController() {
        this.teamService = TeamService.getInstance();
    }

    @Override
    @RequestMapping(uri = "팀등록", method = RequestMethod.POST)
    public void insert(String queryString) {
        HashMap<String, String> map = parameterParser(queryString);
        validateParameter(map);
        teamService.register(map);
    }

    @Override
    @RequestMapping(uri = "팀목록", method = RequestMethod.GET)
    public void select() {
        teamService.show();
    }

    @Override
    public boolean validateParameter(HashMap<String, String> map) {
        if (map.containsKey("name") && map.containsKey("stadiumId"))
            return true;

        throw new IllegalParameterException("알맞은 파라미터명이 아닙니다.");
    }

}
